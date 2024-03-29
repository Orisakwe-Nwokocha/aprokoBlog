package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.*;

public interface UserServices {
    RegisterUserResponse register(RegisterRequest registerRequest);
    LoginUserResponse login(LoginRequest loginRequest);
    LogoutUserResponse logout(LogoutRequest logOutRequest);
    CreatePostResponse createPost(CreatePostRequest createPostRequest);
    EditPostResponse editPost(EditPostRequest editPostRequest);
    DeletePostResponse deletePost(DeletePostRequest deletePostRequest);
    void updatePostViews(UpdatePostViewRequest updatePostViewRequest);
    void save(User user);
    User findUserBy(String username);
}
