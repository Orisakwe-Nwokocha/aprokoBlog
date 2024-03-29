package africa.semicolon.aprokoBlog.dtos.requests;

import lombok.Data;

@Data
public class ViewPostRequest {
    private String viewerUsername;
    private String postId;
}
