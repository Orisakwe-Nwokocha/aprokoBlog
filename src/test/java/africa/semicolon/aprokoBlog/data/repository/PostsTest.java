package africa.semicolon.aprokoBlog.data.repository;

import africa.semicolon.aprokoBlog.data.models.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
@DataMongoTest
public class PostsTest {
    @Autowired
    private Posts posts;

    @Test
    public void postTest(){
        Post post = new Post();
        var savedPost = posts.save(post);
        assertThat(posts.count(), is(1L));
        assertThat(savedPost.getId(), notNullValue());
    }

}