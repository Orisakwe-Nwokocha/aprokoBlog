package africa.semicolon.aprokoBlog.dto.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    public String username;
    public String password;
}