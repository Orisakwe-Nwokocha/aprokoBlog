package africa.semicolon.aprokoBlog.dtos.responses;

import lombok.Data;

@Data
public class CommentResponse {
    private String commenterId;
    private String commenter;
}