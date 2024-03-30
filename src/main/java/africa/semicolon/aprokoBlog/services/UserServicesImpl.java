package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.*;
import africa.semicolon.aprokoBlog.exceptions.IncorrectPasswordException;
import africa.semicolon.aprokoBlog.exceptions.UserExistsException;
import africa.semicolon.aprokoBlog.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Cleaner.cleanup;
import static africa.semicolon.aprokoBlog.utils.Cryptography.encode;
import static africa.semicolon.aprokoBlog.utils.Cryptography.isMatches;
import static africa.semicolon.aprokoBlog.utils.Mapper.*;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private Users users;
    @Autowired
    private PostServices postServices;

    @Override
    public RegisterUserResponse register(RegisterRequest registerRequest) {
        registerRequest.setUsername(cleanup(registerRequest.getUsername()));
        validate(registerRequest.getUsername());
        registerRequest.setPassword(encode(registerRequest.getPassword()));
        User newUser = map(registerRequest);
        User savedUser = users.save(newUser);
        return registerResponseMap(savedUser);
    }

    @Override
    public LoginUserResponse login(LoginRequest loginRequest) {
        User foundUser = findUserBy(loginRequest.getUsername());
        if (!isMatches(loginRequest, foundUser)) throw new IncorrectPasswordException("Password is not correct");
        return mapLoginResponse(foundUser);
    }

    @Override
    public LogoutUserResponse logout(LogoutRequest logOutRequest) {
        User foundUser = findUserBy(logOutRequest.getUsername());
        return mapLogoutResponse(foundUser);
    }

    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        User foundUser = findUserBy(createPostRequest.getUsername());
        Post newPost = postServices.createPostWith(createPostRequest);
        foundUser.getPosts().add(newPost);
        users.save(foundUser);
        return mapCreatePostResponse(newPost);
    }

    @Override
    public ViewPostResponse viewPost(ViewPostRequest viewPostRequest) {
        User viewer = findUserBy(viewPostRequest.getViewer());
        return postServices.addViewWith(viewPostRequest, viewer);
    }

    @Override
    public CommentResponse reactToPost(CommentRequest commentRequest) {
        User commenter = findUserBy(commentRequest.getCommenter());
        return postServices.addCommentWith(commentRequest, commenter);
    }

    @Override
    public GetUserPostsResponse getUserPosts(GetUserPostsRequest getUserPostsRequest) {
        User foundUser = findUserBy(getUserPostsRequest.getUsername());
        return mapGetUserPostsResponse(foundUser);
    }

    private User findUserBy(String username) {
        username = cleanup(username);
        User foundUser = users.findByUsername(username);
        if (foundUser == null) throw new UsernameNotFoundException(String.format("%s not found", username));
        return foundUser;
    }

    private void validate(String username) {
        boolean userExists = users.existsByUsername(username);
        if (userExists) throw new UserExistsException(String.format("%s already exists", username));
    }
}
