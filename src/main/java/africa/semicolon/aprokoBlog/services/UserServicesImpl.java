package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;
import africa.semicolon.aprokoBlog.dtos.responses.RegisterUserResponse;
import africa.semicolon.aprokoBlog.exceptions.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Cleaner.cleanup;
import static africa.semicolon.aprokoBlog.utils.Mapper.map;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private Users users;

    @Override
    public RegisterUserResponse register(RegisterRequest registerRequest) {
        registerRequest.setUsername(cleanup(registerRequest.getUsername()));
        validate(registerRequest.getUsername());
        User newUser = map(registerRequest);
        User savedUser = users.save(newUser);
        return map(savedUser);
    }


    private void validate(String username) {
        boolean userExists = users.existsByUsername(username);
        if (userExists) throw new UserExistsException(String.format("%s already exists", username));
    }


}
