package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.CommentResponse;
import africa.semicolon.aprokoBlog.dtos.responses.DeletePostResponse;
import africa.semicolon.aprokoBlog.dtos.responses.ViewPostResponse;

public interface PostServices {
    Post createPostWith(CreatePostRequest createPostRequest);
    Post editPostWith(EditPostRequest editPostRequest);
    DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest);
    ViewPostResponse viewPost(ViewPostRequest viewPostRequest);
    CommentResponse addComment(CommentRequest commentRequest);
}