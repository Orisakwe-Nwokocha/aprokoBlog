package africa.semicolon.aprokoBlog.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePostRequest {
    @NotNull
    private String username;
    @NotNull
    private String title;
    @NotNull
    private String content;
}