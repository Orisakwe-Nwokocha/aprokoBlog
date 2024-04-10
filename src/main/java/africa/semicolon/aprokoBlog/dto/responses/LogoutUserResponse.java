package africa.semicolon.aprokoBlog.dto.responses;

import lombok.Data;

@Data
public class LogoutUserResponse {
    private String id;
    private String username;
    private boolean isLoggedIn;
}