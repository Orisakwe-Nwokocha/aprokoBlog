package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;
import africa.semicolon.aprokoBlog.dtos.responses.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private Users users;

    @Override
    public RegisterUserResponse register(RegisterRequest registerRequest) {
        User newUser = new User();
        newUser.setFirstName(registerRequest.getFirstName());
        newUser.setLastName(registerRequest.getLastName());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setUsername(registerRequest.getUsername());
        users.save(newUser);

        return null;
    }
}
