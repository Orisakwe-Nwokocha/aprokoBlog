package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.repository.Posts;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class PostServicesTest {
    @Autowired
    private UserServices userServices;
    @Autowired
    private Users users;
    @Autowired
    private PostServices postServices;
    @Autowired
    private Posts posts;
    private RegisterRequest registerRequest;
    private CreatePostRequest createPostRequest;
    private ViewPostRequest viewPostRequest;

    @BeforeEach
    public void setUp() {
        users.deleteAll();

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

    @Test
    public void userViewsCreatedPost_numberOfPostViewsIs1Test() {
        userServices.register(registerRequest);
        userServices.createPost(createPostRequest);
        assertThat(posts.count(), is(1L));
        assertThat(posts.findAll().getFirst().getViews(), hasSize(0));
        var foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        assertThat(foundUser.getPosts().getFirst().getViews(), hasSize(0));

        var viewPostResponse = postServices.viewPost(viewPostRequest);
        assertThat(posts.count(), is(1L));
        assertThat(posts.findAll().getFirst().getViews(), hasSize(1));
        foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        assertThat(foundUser.getPosts().getFirst().getViews(), hasSize(1));
        assertThat(viewPostResponse.getId(), notNullValue());

    }

}