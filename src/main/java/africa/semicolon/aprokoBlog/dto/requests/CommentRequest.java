package africa.semicolon.aprokoBlog.dto.requests;

import lombok.Data;

@Data
public class CommentRequest {
    private String commenter;
    private String postId;
    private String comment;
}