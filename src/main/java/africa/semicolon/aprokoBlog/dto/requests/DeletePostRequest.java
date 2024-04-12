package africa.semicolon.aprokoBlog.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeletePostRequest {
    @NotNull
    private String postId;
    @NotNull
    private String author;
}