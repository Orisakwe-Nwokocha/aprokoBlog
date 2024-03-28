package africa.semicolon.aprokoBlog.utils;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;
import africa.semicolon.aprokoBlog.dtos.responses.RegisterUserResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(registerRequest.getPassword());
        user.setUsername(registerRequest.getUsername());
        return user;
    }
    public static RegisterUserResponse map(User savedUser) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(savedUser.getId());
        registerUserResponse.setUsername(savedUser.getUsername());
        registerUserResponse.setDateRegistered(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(savedUser.getDateRegistered()));
        return registerUserResponse;
    }
}
