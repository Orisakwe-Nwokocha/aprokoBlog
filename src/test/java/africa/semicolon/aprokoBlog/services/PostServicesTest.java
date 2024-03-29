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

        createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("title");
        createPostRequest.setContent("content");

        viewPostRequest = new ViewPostRequest();
        commentRequest = new CommentRequest();
    }

    @Test
    public void userViewsCreatedPost_numberOfPostViewsIs1Test() {
        userServices.register(registerRequest);
        var createPostResponse = userServices.createPost(createPostRequest);
        assertThat(posts.count(), is(1L));
        assertThat(posts.findAll().getFirst().getViews(), hasSize(0));
        String postAuthor = registerRequest.getUsername().toLowerCase();
        var foundUser = users.findByUsername(postAuthor);
        assertThat(foundUser.getPosts().getFirst().getViews(), hasSize(0));

        registerRequest.setUsername("username2");
        userServices.register(registerRequest);
        viewPostRequest.setViewer(registerRequest.getUsername());
        viewPostRequest.setPostAuthor(postAuthor);
        viewPostRequest.setPostId(createPostResponse.getPostId());

        var viewPostResponse = postServices.viewPost(viewPostRequest);
        assertThat(posts.count(), is(1L));
        assertThat(posts.findAll().getFirst().getViews(), hasSize(1));
        foundUser = users.findByUsername(postAuthor);
        assertThat(foundUser.getPosts().getFirst().getViews(), hasSize(1));
        assertThat(viewPostResponse.getViewerId(), notNullValue());

    }
    @Test
    public void userCommentsOnCreatedPost_numberOfPostCommentsIs1Test() {
        userServices.register(registerRequest);
        var createPostResponse = userServices.createPost(createPostRequest);
        assertThat(posts.count(), is(1L));
        assertThat(posts.findAll().getFirst().getComments(), hasSize(0));
        String postAuthor = registerRequest.getUsername().toLowerCase();
        var foundUser = users.findByUsername(postAuthor);
        assertThat(foundUser.getPosts().getFirst().getComments(), hasSize(0));

        registerRequest.setUsername("username2");
        userServices.register(registerRequest);
        commentRequest.setCommenter(registerRequest.getUsername());
        commentRequest.setPostAuthor(postAuthor);
        commentRequest.setPostId(createPostResponse.getPostId());
        commentRequest.setComment("comment");

        var commentResponse = postServices.addComment(commentRequest);
        assertThat(posts.count(), is(1L));
        assertThat(posts.findAll().getFirst().getViews(), hasSize(1));
        assertThat(posts.findAll().getFirst().getComments(), hasSize(1));
        foundUser = users.findByUsername(postAuthor);
        assertThat(foundUser.getPosts().getFirst().getViews(), hasSize(1));
        assertThat(foundUser.getPosts().getFirst().getComments(), hasSize(1));
        assertThat(commentResponse.getCommenterId(), notNullValue());

    }

}