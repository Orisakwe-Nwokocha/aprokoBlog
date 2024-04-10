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
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
public class PostControllersTest {
    @Autowired
    private PostControllers postControllers;
    @Autowired
    private UserControllers userControllers;
    @Autowired
    private Users users;
    @Autowired
    private Posts posts;

    private RegisterRequest registerRequest;
    private CreatePostRequest createPostRequest;


    @BeforeEach
    public void setUp() {
        users.deleteAll();
        posts.deleteAll();

        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("title");
        createPostRequest.setContent("content");
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
    public void getViewsCount_isSuccessful_isTrue() {
        userControllers.register(registerRequest);
        var response = userControllers.createPost(createPostRequest);
        ViewsCountRequest viewsCountRequest = new ViewsCountRequest();
        viewsCountRequest.setPostId(getCreatedPostId(response));
        response = postControllers.getViewsCount(viewsCountRequest);
        assertIsSuccessful(response, true);
        assertThat(response.getStatusCode(), is(OK));
    }

    @Test
    public void getViewsCount_isSuccessful_isFalse() {
        userControllers.register(registerRequest);
        ViewsCountRequest viewsCountRequest = new ViewsCountRequest();
        viewsCountRequest.setPostId("nonExistingPostId");
        var response = postControllers.getViewsCount(viewsCountRequest);
        assertIsSuccessful(response, false);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));
    }
}