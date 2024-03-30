package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.*;

public interface PostServices {
    Post createPostWith(CreatePostRequest createPostRequest);
    EditPostResponse editPostWith(EditPostRequest editPostRequest);
    DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest);
    ViewPostResponse addViewWith(ViewPostRequest viewPostRequest, User viewer);
    CommentResponse addCommentWith(CommentRequest commentRequest, User commenter);
    ViewsCountResponse getNumberOfViews(ViewsCountRequest viewsCountRequest);
}