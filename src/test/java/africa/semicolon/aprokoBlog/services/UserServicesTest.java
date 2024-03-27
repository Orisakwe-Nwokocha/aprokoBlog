package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;
import africa.semicolon.aprokoBlog.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UserServicesTest {
    @Autowired
    private UserServices userServices;
    @Autowired
    private Users users;

    @BeforeEach
    public void setUp() {
        users.deleteAll();
    }

    @Test
    public void registerUser_numberOfUsersIsOne() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        assertThat(users.count(), is(0L));
        userServices.register(registerRequest);
        assertThat(users.count(), is(1L));
    }

    @Test
    public void registerSameUser_throwsUserExistsException() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        userServices.register(registerRequest);
        try {
            userServices.register(registerRequest);
        }
        catch (UserExistsException e) {
            assertThat(e.getMessage(), containsString("username already exists"));
        }
        assertThat(users.count(), is(1L));
    }
}