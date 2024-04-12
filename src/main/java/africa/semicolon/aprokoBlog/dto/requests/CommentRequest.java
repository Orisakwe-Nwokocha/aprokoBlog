package africa.semicolon.aprokoBlog.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {
    @NotNull
    private String commenter;
    @NotNull
    private String postId;
    @NotNull
    private String comment;
}