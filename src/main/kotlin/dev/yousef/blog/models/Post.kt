package dev.yousef.blog.models

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InvalidObjectException


@Entity
class Post : PanacheEntity() {

    var title: String? = null
    var body: String? = null

    @ManyToOne(fetch = FetchType.EAGER)
    val subcategory: Subcategory? = null


    var postStatus: PostStatus? = null
    var postLanguage: PostLanguage? = null

    @ManyToOne(fetch = FetchType.EAGER)
    val programmingLanguage: ProgrammingLanguage? = null

    companion object : PanacheCompanion<Post> {
        fun create(post: Post): Post {
            if (post.isValid()) {
                post.persist<Post>()
                return post
            } else {
                throw InvalidObjectException("Post object is invalid")
            }
        }

        fun listAllPosts(): List<Post> = Post.listAll().await().indefinitely()
    }


}


enum class PostStatus {
    DRAFT, PUBLISHED, ARCHIVED, DELETED
}


enum class PostLanguage {
    ENGLISH, ARABIC
}

fun Post.isValid(): Boolean {
    return title != null &&
            body != null &&
            postStatus != null &&
            postLanguage != null &&
            // check if rest of fields are not null
            this.subcategory != null &&
            this.programmingLanguage != null
}


