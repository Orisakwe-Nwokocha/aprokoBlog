package africa.semicolon.aprokoBlog.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LogoutRequest {
    @NotNull
    private String username;
}