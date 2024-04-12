package africa.semicolon.aprokoBlog.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditPostRequest {
    @NotNull
    private String author;
    @NotNull
    private String postId;
    @NotNull
    private String title;
    @NotNull
    private String content;
}