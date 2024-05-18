package dev.yousef.blog.models

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InvalidObjectException


@Entity
class Post : PanacheEntity() {

    var title: String? = null
    var body: String? = null
    val subcategoryId: Int = 0
    var postStatus: PostStatus? = null
    var postLanguage: PostLanguage? = null
    val programmingLanguageId: Int = 0

    companion object : PanacheCompanion<Post> {
        suspend fun create(post: Post) = withContext(Dispatchers.IO) {
            if (post.isValid()) {
                post.persist<Post>()
                post
            } else {
                throw InvalidObjectException("Post object is invalid")
            }
        }
        //TODO: The rest of the service methods
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
            this.subcategoryId != 0 &&
            this.programmingLanguageId != 0
}


