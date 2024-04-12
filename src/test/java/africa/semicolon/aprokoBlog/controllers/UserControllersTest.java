package africa.semicolon.aprokoBlog.controllers;

import africa.semicolon.aprokoBlog.data.repository.Posts;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dto.requests.*;
import africa.semicolon.aprokoBlog.dto.responses.ApiResponse;
import africa.semicolon.aprokoBlog.dto.responses.CreatePostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.*;


@SpringBootTest
public class UserControllersTest {
    @Autowired
    private UserControllers userControllers;
    @Autowired
    private Users users;
    @Autowired
    private Posts posts;

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private LogoutRequest logoutRequest;
    private CreatePostRequest createPostRequest;
    private EditPostRequest editPostRequest;
    private DeletePostRequest deletePostRequest;

    @BeforeEach
    public void setUp() {
        users.deleteAll();
        posts.deleteAll();

        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("username");

        createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("title");
        createPostRequest.setContent("content");

        editPostRequest = new EditPostRequest();
        editPostRequest.setAuthor("username");
        editPostRequest.setTitle("title");
        editPostRequest.setContent("newContent");

        deletePostRequest = new DeletePostRequest();
        deletePostRequest.setAuthor("username");
    }

    private static void assertIsSuccessful(ResponseEntity<?> response, boolean expected) {
        if (response.hasBody() && response.getBody() instanceof ApiResponse apiResponse) {
            boolean success = apiResponse.isSuccessful();
            assertThat(success, is(expected));
        }
    }

    private static String getCreatedPostId(ResponseEntity<?> response) {
        if (response.getBody() instanceof ApiResponse apiResponse) {
            if (apiResponse.getData() instanceof CreatePostResponse createPostResponse) return createPostResponse.getPostId();
        }
        throw new IllegalArgumentException("Error");
    }

    @Test
    public void testRegister_isSuccessful_isTrue() {
        var response = userControllers.register(registerRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(CREATED));
    }

    @Test
    public void testRegister_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        var response = userControllers.register(registerRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    public void testLogin_isSuccessful_isTrues() {
        userControllers.register(registerRequest);
        var response = userControllers.login(loginRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(OK));
    }

    @Test
    public void testLogin_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        loginRequest.setPassword("wrongPassword");
        var response = userControllers.login(loginRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    public void testLogout_isSuccessful_isTrue() {
        userControllers.register(registerRequest);
        var response = userControllers.logout(logoutRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(OK));
    }

    @Test
    public void testLogout_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        logoutRequest.setUsername("nonExistingUsername");
        var response = userControllers.logout(logoutRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    public void testCreatePost_isSuccessful_isTrue() {
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(CREATED));
    }

    @Test
    public void testCreatePost_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        userControllers.logout(logoutRequest);
        var response = userControllers.createPost(createPostRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    public void testEditPost_isSuccessful_isTrue() {
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        editPostRequest.setPostId(getCreatedPostId(response));
        response = userControllers.editPost(editPostRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(OK));
    }

    @Test
    public void testEditPost_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        editPostRequest.setAuthor("wrongAuthor");
        editPostRequest.setPostId(getCreatedPostId(response));
        response = userControllers.editPost(editPostRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    public void testDeletePost_isSuccessful_isTrue() {
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        deletePostRequest.setPostId(getCreatedPostId(response));
        response = userControllers.deletePost(deletePostRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(OK));
    }
    @Test
    public void testDeletePost_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        deletePostRequest.setAuthor("wrongAuthor");
        deletePostRequest.setPostId(getCreatedPostId(response));
        response = userControllers.deletePost(deletePostRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    public void testViewPost_isSuccessful_isTrue() {
        ViewPostRequest viewPostRequest = new ViewPostRequest();
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        viewPostRequest.setPostId(getCreatedPostId(response));

        registerRequest.setUsername("anotherUser");
        userControllers.register(registerRequest);
        viewPostRequest.setViewer("anotherUser");

        response = userControllers.viewPost(viewPostRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(OK));
    }

    @Test
    public void testViewPost_isSuccessful_isFalse() {
        ViewPostRequest viewPostRequest = new ViewPostRequest();
        userControllers.register(registerRequest);
        viewPostRequest.setPostId("");
        var response = userControllers.viewPost(viewPostRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    public void testAddComment_isSuccessful_isTrue() {
        CommentRequest commentRequest = new CommentRequest();
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        commentRequest.setPostId(getCreatedPostId(response));

        registerRequest.setUsername("anotherUser");
        userControllers.register(registerRequest);
        commentRequest.setCommenter("anotherUser");
        commentRequest.setComment("comment");

        response = userControllers.addComment(commentRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(OK));
    }

    @Test
    public void testAddComment_isSuccessful_isFalse() {
        CommentRequest commentRequest = new CommentRequest();
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        commentRequest.setPostId(getCreatedPostId(response));
        commentRequest.setCommenter("anotherUser");
        commentRequest.setComment("comment");
        response = userControllers.addComment(commentRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    public void getUserPosts_isSuccessful_isTrue() {
        userControllers.register(registerRequest);
        userControllers.createPost(createPostRequest);
        GetUserPostsRequest getUserPostsRequest = new GetUserPostsRequest();
        getUserPostsRequest.setUsername("username");
        var response = userControllers.getUserPosts(getUserPostsRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(OK));
    }

    @Test
    public void getUserPosts_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        userControllers.createPost(createPostRequest);
        GetUserPostsRequest getUserPostsRequest = new GetUserPostsRequest();
        getUserPostsRequest.setUsername("nonExistingUser");
        var response = userControllers.getUserPosts(getUserPostsRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }
}