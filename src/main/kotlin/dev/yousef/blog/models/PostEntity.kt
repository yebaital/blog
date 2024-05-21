package dev.yousef.blog.models

import dev.yousef.blog.grpc.Post
import dev.yousef.blog.grpc.Post.GetPostRequest
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import java.io.InvalidObjectException


@Entity
class PostEntity : PanacheEntity() {

    lateinit var title: String
    lateinit var body: String

    @ManyToOne(fetch = FetchType.EAGER)
    lateinit var subcategory: SubcategoryEntity


    lateinit var postStatus: PostStatus
    lateinit var postLanguage: PostLanguage

    @ManyToOne(fetch = FetchType.EAGER)
    lateinit var programmingLanguage: ProgrammingLanguageEntity

    companion object : PanacheCompanion<PostEntity> {

        private fun PostEntity.isValid(): Boolean {
            return ::title.isInitialized &&
                    ::postStatus.isInitialized &&
                    ::postLanguage.isInitialized &&
                    ::subcategory.isInitialized &&
                    ::programmingLanguage.isInitialized
        }

        fun toEntityPostStatus(grpcPostStatus: Post.PostStatus): PostStatus {
            return when (grpcPostStatus) {
                Post.PostStatus.DRAFT -> PostStatus.DRAFT
                Post.PostStatus.PUBLISHED -> PostStatus.PUBLISHED
                Post.PostStatus.ARCHIVED -> PostStatus.ARCHIVED
                Post.PostStatus.DELETED -> PostStatus.DELETED
                Post.PostStatus.UNRECOGNIZED -> throw Exception("Post status unrecognized")
            }
        }

        fun toEntityPostLanguage(grpcPostLanguage: Post.PostLanguage): PostLanguage {
            return when (grpcPostLanguage) {
                Post.PostLanguage.ENGLISH -> PostLanguage.ENGLISH
                Post.PostLanguage.ARABIC -> PostLanguage.ARABIC
                Post.PostLanguage.UNRECOGNIZED -> throw Exception("Post language unrecognized")
            }
        }

        fun toPostEntity(
            postRequest: Post.CreatePostRequest,
            newSubcategory: SubcategoryEntity,
            newProgrammingLanguage: ProgrammingLanguageEntity
        ): PostEntity {
            val newPost = PostEntity()
            return newPost.apply {
                title = postRequest.title
                body = postRequest.body
                subcategory = newSubcategory
                postStatus = toEntityPostStatus(postRequest.postStatus)
                this.postLanguage = toEntityPostLanguage(postRequest.postLanguage)
                programmingLanguage = newProgrammingLanguage
            }
        }

        fun create(postEntity: PostEntity): PostEntity {
            if (postEntity.isValid()) {
                postEntity.persist<PostEntity>()
                return postEntity
            } else {
                throw InvalidObjectException("Post object is invalid")
            }
        }

        fun listAllPosts(): Uni<List<PostEntity>> = PostEntity.listAll()
    }

    fun PostEntity.toGRPCPostStatus() {
        when (this.postStatus) {
            PostStatus.PUBLISHED -> Post.PostStatus.PUBLISHED
            PostStatus.DRAFT -> Post.PostStatus.DRAFT
            PostStatus.ARCHIVED -> Post.PostStatus.ARCHIVED
            PostStatus.DELETED -> Post.PostStatus.DELETED
        }
    }

    fun PostEntity.toGRPCPostLanguage() {
        when (this.postLanguage) {
            PostLanguage.ARABIC -> Post.PostLanguage.ARABIC
            PostLanguage.ENGLISH -> Post.PostLanguage.ENGLISH
        }
    }

}

enum class PostStatus {
    DRAFT, PUBLISHED, ARCHIVED, DELETED
}


enum class PostLanguage {
    ENGLISH, ARABIC
}




