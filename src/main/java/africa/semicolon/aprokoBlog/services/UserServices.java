package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.LogoutRequest;
import africa.semicolon.aprokoBlog.dtos.requests.LoginRequest;
import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;
import africa.semicolon.aprokoBlog.dtos.responses.CreatePostResponse;
import africa.semicolon.aprokoBlog.dtos.responses.LoginUserResponse;
import africa.semicolon.aprokoBlog.dtos.responses.LogoutUserResponse;
import africa.semicolon.aprokoBlog.dtos.responses.RegisterUserResponse;

public interface UserServices {
    RegisterUserResponse register(RegisterRequest registerRequest);
    LoginUserResponse login(LoginRequest loginRequest);
    LogoutUserResponse logout(LogoutRequest logOutRequest);
    CreatePostResponse createPost(CreatePostRequest createPostRequest);
}
