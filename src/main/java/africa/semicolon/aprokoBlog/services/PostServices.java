package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.DeletePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.EditPostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.ViewPostRequest;
import africa.semicolon.aprokoBlog.dtos.responses.DeletePostResponse;
import africa.semicolon.aprokoBlog.dtos.responses.ViewPostResponse;

public interface PostServices {
    Post createPostWith(CreatePostRequest createPostRequest);
    Post editPostWith(EditPostRequest editPostRequest);
    DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest);
    ViewPostResponse viewPost(ViewPostRequest viewPostRequest);
}
