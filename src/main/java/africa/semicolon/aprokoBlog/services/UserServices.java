package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.*;

public interface UserServices {
    RegisterUserResponse register(RegisterRequest registerRequest);
    LoginUserResponse login(LoginRequest loginRequest);
    LogoutUserResponse logout(LogoutRequest logOutRequest);
    CreatePostResponse createPost(CreatePostRequest createPostRequest);
    ViewPostResponse viewPost(ViewPostRequest viewPostRequest);
    CommentResponse reactToPost(CommentRequest commentRequest);
    GetUserPostsResponse getUserPosts(GetUserPostsRequest getUserPostsRequest);
}
