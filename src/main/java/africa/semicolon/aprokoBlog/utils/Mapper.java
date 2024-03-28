package africa.semicolon.aprokoBlog.utils;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.EditPostRequest;
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

    public static LoginUserResponse mapLoginResponse(User user) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        loginUserResponse.setId(user.getId());
        loginUserResponse.setUsername(user.getUsername());
        return loginUserResponse;
    }

    public static LogoutUserResponse mapLogoutResponse(User user) {
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

    public static CreatePostResponse mapCreatePostResponse(Post post) {
        CreatePostResponse createPostResponse = new CreatePostResponse();
        createPostResponse.setId(post.getId());
        createPostResponse.setTitle(post.getTitle());
        createPostResponse.setContent(post.getContent());
        createPostResponse.setDateCreated(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(post.getDateCreated()));
        return createPostResponse;
    }

    public static Post map(EditPostRequest editPostRequest) {
        Post post = new Post();
        post.setId(editPostRequest.getId());
        post.setTitle(editPostRequest.getTitle());
        post.setContent(editPostRequest.getContent());
        return post;
    }

    public static EditPostResponse mapEditPostResponse(Post post) {
        EditPostResponse editPostResponse = new EditPostResponse();
        editPostResponse.setId(post.getId());
        editPostResponse.setTitle(post.getTitle());
        editPostResponse.setContent(post.getContent());
        editPostResponse.setDateCreated(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(post.getDateCreated()));
        return editPostResponse;
    }

    public static DeletePostResponse mapDeletePostResponse(Post post) {
        DeletePostResponse deletePostResponse = new DeletePostResponse();
        deletePostResponse.setId(post.getId());
        return deletePostResponse;
    }
}
