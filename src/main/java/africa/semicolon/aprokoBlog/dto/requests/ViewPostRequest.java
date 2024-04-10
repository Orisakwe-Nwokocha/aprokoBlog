package africa.semicolon.aprokoBlog.dto.requests;

import lombok.Data;

@Data
public class ViewPostRequest {
    private String viewer;
    private String postId;
}