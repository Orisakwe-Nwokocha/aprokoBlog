package africa.semicolon.aprokoBlog.dtos.requests;

import lombok.Data;

@Data
public class EditPostRequest {
    private String postId;
    private String author;
    private String title;
    private String content;
}