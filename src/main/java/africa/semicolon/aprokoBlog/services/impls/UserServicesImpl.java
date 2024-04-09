package africa.semicolon.aprokoBlog.services.impls;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.*;
import africa.semicolon.aprokoBlog.exceptions.*;
import africa.semicolon.aprokoBlog.services.PostServices;
import africa.semicolon.aprokoBlog.services.UserServices;
import jakarta.annotation.PostConstruct;
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
        validate(registerRequest);
        registerRequest.setUsername(cleanup(registerRequest.getUsername()));
        registerRequest.setPassword(encode(registerRequest.getPassword()));
        User newUser = map(registerRequest);
        User savedUser = users.save(newUser);
        return mapRegisterResponseWith(savedUser);
    }

    @Override
    public LoginUserResponse login(LoginRequest loginRequest) {
        User foundUser = findUserBy(loginRequest.getUsername());
        if (!isMatches(loginRequest, foundUser)) throw new IncorrectPasswordException("Password is not correct");
        foundUser.setLoggedIn(true);
        User savedUser = users.save(foundUser);
        return mapLoginResponseWith(savedUser);
    }

    @Override
    public LogoutUserResponse logout(LogoutRequest logOutRequest) {
        User foundUser = findUserBy(logOutRequest.getUsername());
        foundUser.setLoggedIn(false);
        User savedUser = users.save(foundUser);
        return mapLogoutResponseWith(savedUser);
    }
    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        User foundUser = findUserBy(createPostRequest.getUsername());
        validateLoginStatusOf(foundUser);
        Post newPost = postServices.createPostWith(createPostRequest);
        foundUser.getPosts().add(newPost);
        users.save(foundUser);
        return mapCreatePostResponseWith(newPost);
    }

    @Override
    public EditPostResponse editPostWith(EditPostRequest editPostRequest) {
        User author = findUserBy(editPostRequest.getAuthor());
        validateLoginStatusOf(author);
        Post authorPost = findUserPostBy(editPostRequest.getPostId(), author);
        return postServices.editPostWith(editPostRequest, authorPost);
    }

    @Override
    public DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest) {
        User author = findUserBy(deletePostRequest.getAuthor());
        validateLoginStatusOf(author);
        Post authorPost = findUserPostBy(deletePostRequest.getPostId(), author);
        return postServices.deletePostWith(deletePostRequest, authorPost);
    }

    @Override
    public ViewPostResponse viewPost(ViewPostRequest viewPostRequest) {
        instantiateAnonymousUser();
        if (viewPostRequest.getViewer() == null)
            return postServices.addViewWith(viewPostRequest, findUserBy("anonymousUser"));
        User viewer = findUserBy(viewPostRequest.getViewer());
        return postServices.addViewWith(viewPostRequest, viewer);
    }

    @Override
    public CommentResponse reactToPost(CommentRequest commentRequest) {
        User commenter = findUserBy(commentRequest.getCommenter());
        validateLoginStatusOf(commenter);
        return postServices.addCommentWith(commentRequest, commenter);
    }

    @Override
    public GetUserPostsResponse getUserPosts(GetUserPostsRequest getUserPostsRequest) {
        User foundUser = findUserBy(getUserPostsRequest.getUsername());
        return mapGetUserPostsResponse(foundUser);
    }

    @PostConstruct
    private void instantiateAnonymousUser() {
        String username = cleanup("anonymousUser");
        if (!users.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setPosts(null);
            users.save(user);
        }
    }

    private void validateLoginStatusOf(User user) {
        if (!user.isLoggedIn()) throw new IllegalUserStateException("User is not logged in");
    }

    private Post findUserPostBy(String postId, User author) {
        for (Post post : author.getPosts()) if (post.getId().equals(postId)) return post;
        throw new PostNotFoundException("Post not found");
    }

    private User findUserBy(String username) {
        username = cleanup(username);
        User foundUser = users.findByUsername(username);
        if (foundUser == null) throw new UsernameNotFoundException(String.format("%s not found", username));
        return foundUser;
    }

    private void validateUniqueUsername(RegisterRequest registerRequest) {
        String username = cleanup(registerRequest.getUsername());
        boolean userExists = users.existsByUsername(username);
        if (userExists) throw new UserExistsException(String.format("%s already exists", username));
    }

    private static void validateBlank(RegisterRequest registerRequest) {
        boolean isBlank = registerRequest.getUsername().isBlank() || registerRequest.getPassword().isBlank();
        if (isBlank) throw new InvalidArgumentException("Registration details cannot be blank");
    }

    private static void validateNull(RegisterRequest registerRequest) {
        boolean isNull = registerRequest.getUsername() == null || registerRequest.getPassword() == null;
        if (isNull) throw new InvalidArgumentException("Registration details are required");
    }

    private void validate(RegisterRequest registerRequest) {
        validateNull(registerRequest);
        validateBlank(registerRequest);
        validateUniqueUsername(registerRequest);
    }
}
