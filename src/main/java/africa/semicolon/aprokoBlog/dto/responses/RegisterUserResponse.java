package africa.semicolon.aprokoBlog.dto.responses;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private String id;
    private String username;
    private String dateTimeRegistered;
}