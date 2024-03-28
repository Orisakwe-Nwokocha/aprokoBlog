package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.LoginRequest;
import africa.semicolon.aprokoBlog.dtos.requests.LogoutRequest;
import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;
import africa.semicolon.aprokoBlog.dtos.responses.LoginUserResponse;
import africa.semicolon.aprokoBlog.dtos.responses.LogoutUserResponse;
import africa.semicolon.aprokoBlog.dtos.responses.RegisterUserResponse;
import africa.semicolon.aprokoBlog.exceptions.IncorrectPasswordException;
import africa.semicolon.aprokoBlog.exceptions.UserExistsException;
import africa.semicolon.aprokoBlog.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Cleaner.cleanup;
import static africa.semicolon.aprokoBlog.utils.Cryptography.*;
import static africa.semicolon.aprokoBlog.utils.Mapper.*;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private Users users;

    @Override
    public RegisterUserResponse register(RegisterRequest registerRequest) {
        registerRequest.setUsername(cleanup(registerRequest.getUsername()));
        validate(registerRequest.getUsername());
        registerRequest.setPassword(encode(registerRequest.getPassword()));
        User newUser = registerResponseMap(registerRequest);
        User savedUser = users.save(newUser);
        return registerResponseMap(savedUser);
    }

    @Override
    public LoginUserResponse login(LoginRequest loginRequest) {
        User foundUser = findUserBy(loginRequest.getUsername());
        if (!isMatches(loginRequest, foundUser)) throw new IncorrectPasswordException("Password is not correct");
        return loginResponseMap(foundUser);
    }

    @Override
    public LogoutUserResponse logout(LogoutRequest logOutRequest) {
        User foundUser = findUserBy(logOutRequest.getUsername());
        return logoutResponseMap(foundUser);
    }

    private User findUserBy(String username) {
        username = cleanup(username);
        User foundUser = users.findByUsername(username);
        if (foundUser == null) throw new UsernameNotFoundException(String.format("%s not found", username));
        return foundUser;
    }

    private void validate(String username) {
        boolean userExists = users.existsByUsername(username);
        if (userExists) throw new UserExistsException(String.format("%s already exists", username));
    }


}
