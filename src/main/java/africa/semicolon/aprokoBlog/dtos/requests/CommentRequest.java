package africa.semicolon.aprokoBlog.dtos.requests;

import lombok.Data;

@Data
public class CommentRequest {
    private String commenter;
    private String postId;
    private String postAuthor;
    private String comment;
}