package africa.semicolon.aprokoBlog.data.repository;

import africa.semicolon.aprokoBlog.data.models.View;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataMongoTest
public class ViewsTest {
    @Autowired
    private Views views;

    @Test
    public void viewsTest(){
        assertThat(views.count(), is(0L));
        View newView = new View();
        var savedView = views.save(newView);
        assertThat(views.count(), is(1L));
        assertThat(savedView.getId(), notNullValue());
    }

}