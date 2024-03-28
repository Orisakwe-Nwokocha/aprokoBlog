package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;

public interface PostServices {
    Post createPost(CreatePostRequest createPostRequest);
}
