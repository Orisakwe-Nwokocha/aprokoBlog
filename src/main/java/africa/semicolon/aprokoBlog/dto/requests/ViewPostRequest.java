package africa.semicolon.aprokoBlog.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ViewPostRequest {
    @NotNull
    private String viewer;
    @NotNull
    private String postId;
}