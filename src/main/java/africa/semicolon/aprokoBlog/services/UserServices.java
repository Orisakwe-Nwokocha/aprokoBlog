package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.dtos.requests.RegisterRequest;
import africa.semicolon.aprokoBlog.dtos.responses.RegisterUserResponse;

public interface UserServices {
    RegisterUserResponse register(RegisterRequest registerRequest);
}
