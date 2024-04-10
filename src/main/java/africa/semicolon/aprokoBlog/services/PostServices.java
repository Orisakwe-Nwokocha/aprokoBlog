package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dto.requests.*;
import africa.semicolon.aprokoBlog.dto.responses.*;

public interface PostServices {
    Post createPostWith(CreatePostRequest createPostRequest);
    EditPostResponse editPostWith(EditPostRequest editPostRequest, Post authorPost);
    DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest, Post authorPost);
    ViewPostResponse addViewWith(ViewPostRequest viewPostRequest, User viewer);
    CommentResponse addCommentWith(CommentRequest commentRequest, User commenter);
    ViewsCountResponse getNumberOfViews(ViewsCountRequest viewsCountRequest);
}