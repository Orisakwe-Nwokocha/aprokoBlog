package africa.semicolon.aprokoBlog.controllers;

import africa.semicolon.aprokoBlog.data.repository.Posts;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    private CreatePostRequest createPostRequest;
    private EditPostRequest editPostRequest;
    private DeletePostRequest deletePostRequest;
    private ViewPostRequest viewPostRequest;
    private CommentRequest commentRequest;


    @BeforeEach
    public void setUp() {
        users.deleteAll();
        posts.deleteAll();

        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
    }

    @Test
    public void testRegister_isSuccessful_isTrue() {
        var response = userControllers.register(registerRequest);
        assertThat(response.getStatusCode(), is(CREATED));
    }

    @Test
    public void testRegister_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        var response = userControllers.register(registerRequest);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }

    @Test
    void createPost() {
    }

    @Test
    void editPost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void testViewPost() {
    }

    @Test
    void addComment() {
    }

    @Test
    void getUserPosts() {
    }
}