package africa.semicolon.aprokoBlog.utils;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(registerRequest.getPassword());
        user.setUsername(registerRequest.getUsername());
        return user;
    }
}
