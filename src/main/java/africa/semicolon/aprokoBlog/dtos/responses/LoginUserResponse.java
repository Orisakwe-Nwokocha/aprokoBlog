package africa.semicolon.aprokoBlog.dtos.responses;

import lombok.Data;

@Data
public class LoginUserResponse {
    private String id;
    private String username;
    private boolean isLoggedIn;
}