package africa.semicolon.aprokoBlog.utils;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;
import africa.semicolon.aprokoBlog.dtos.responses.*;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(registerRequest.getPassword());
        user.setUsername(registerRequest.getUsername());
        return user;
    }
    public static RegisterUserResponse registerResponseMap(User user) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(user.getId());
        registerUserResponse.setUsername(user.getUsername());
        registerUserResponse.setDateTimeRegistered(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(user.getDateRegistered()));
        return registerUserResponse;
    }

    public static LoginUserResponse loginResponseMap(User user) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        loginUserResponse.setId(user.getId());
        loginUserResponse.setUsername(user.getUsername());
        return loginUserResponse;
    }

    public static LogoutUserResponse logoutResponseMap(User user) {
        LogoutUserResponse logoutUserResponse = new LogoutUserResponse();
        logoutUserResponse.setId(user.getId());
        logoutUserResponse.setUsername(user.getUsername());
        return logoutUserResponse;
    }

    public static Post map(CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setTitle(createPostRequest.getTitle());
        post.setContent(createPostRequest.getContent());
        return post;
    }

    public static CreatePostResponse createPostResponseMap(Post post) {
        CreatePostResponse createPostResponse = new CreatePostResponse();
        createPostResponse.setTitle(post.getTitle());
        createPostResponse.setContent(post.getContent());
        createPostResponse.setDateCreated(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(post.getDateCreated()));
        return createPostResponse;
    }
}
