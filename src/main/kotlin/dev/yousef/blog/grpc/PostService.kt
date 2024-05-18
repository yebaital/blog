package dev.yousef.blog.grpc

import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni


@GrpcService
class PostGRPCService:PostService {
    override fun createPost(request: Post.CreatePostRequest?): Uni<Post.CreatePostResponse> {
        TODO("Not yet implemented")
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