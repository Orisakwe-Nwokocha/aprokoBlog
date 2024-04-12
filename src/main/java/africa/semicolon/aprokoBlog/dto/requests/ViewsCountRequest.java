package africa.semicolon.aprokoBlog.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ViewsCountRequest {
    @NotNull
    private String postId;
}
