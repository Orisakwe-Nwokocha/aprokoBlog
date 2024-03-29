package africa.semicolon.aprokoBlog.data.repository;

import africa.semicolon.aprokoBlog.data.models.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataMongoTest
public class CommentsTest {
    @Autowired
    private Comments comments;

    @Test
    public void commentsTest(){
        assertThat(comments.count(), is(0L));
        Comment newComment = new Comment();
        var savedComment = comments.save(newComment);
        assertThat(comments.count(), is(1L));
        assertThat(savedComment.getId(), notNullValue());
    }

}