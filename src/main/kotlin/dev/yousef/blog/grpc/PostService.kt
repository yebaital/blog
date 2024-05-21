package dev.yousef.blog.grpc

import dev.yousef.blog.grpc.Post.CreatePostResponse
import dev.yousef.blog.models.PostEntity
import dev.yousef.blog.models.ProgrammingLanguageEntity
import dev.yousef.blog.models.SubcategoryEntity
import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.asUni
import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.dispatcher
import jakarta.ws.rs.BadRequestException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import org.jboss.logging.annotations.Pos
import org.jboss.resteasy.reactive.server.runtime.kotlin.ApplicationCoroutineScope


@OptIn(ExperimentalCoroutinesApi::class)
@GrpcService
class PostGRPCService(private val coroutineScope: ApplicationCoroutineScope, private val vertx: Vertx) : PostService {

    private fun PostEntity.toResponse(): CreatePostResponse = CreatePostResponse.newBuilder().apply {
        id = this@toResponse.id.toString()
    }.build()

    override fun createPost(request: Post.CreatePostRequest): Uni<CreatePostResponse> {
        return coroutineScope.async(vertx.dispatcher()) {

            //TODO: Extract to PostDataService in services folder
            val subcategory = SubcategoryEntity.findById(request.subcategoryId.toString()).await().indefinitely()
            val programmingLanguage =
                ProgrammingLanguageEntity.findById(request.programmingLanguageId.toString()).await().indefinitely()

            if (subcategory != null && programmingLanguage != null) {
                val post = PostEntity.toPostEntity(request, subcategory, programmingLanguage)
                return@async PostEntity.create(post).toResponse()
            } else {
                throw BadRequestException("Invalid subcategory or programming language")
            }

        }.asUni()
    }

    override fun getPost(request: Post.GetPostRequest?): Uni<Post.GetPostResponse> {
        TODO("Not yet implemented")
    }

    override fun updatePost(request: Post.UpdatePostRequest?): Uni<Post.UpdatePostResponse> {
        TODO("Not yet implemented")
    }

    override fun deletePost(request: Post.DeletePostRequest?): Uni<Post.DeletePostResponse> {
        TODO("Not yet implemented")
    }

    override fun listPosts(request: Post.ListPostsRequest?): Uni<Post.ListPostsResponse> {
        TODO("Not yet implemented")
    }
}