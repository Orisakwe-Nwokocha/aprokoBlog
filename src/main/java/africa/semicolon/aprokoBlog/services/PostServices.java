package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.DeletePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.EditPostRequest;
import africa.semicolon.aprokoBlog.dtos.responses.DeletePostResponse;

public interface PostServices {
    Post createPost(CreatePostRequest createPostRequest);
    Post editPost(EditPostRequest editPostRequest);
    DeletePostResponse deletePost(DeletePostRequest deletePostRequest);
}
