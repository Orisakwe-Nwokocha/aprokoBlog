package africa.semicolon.aprokoBlog.dto.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}