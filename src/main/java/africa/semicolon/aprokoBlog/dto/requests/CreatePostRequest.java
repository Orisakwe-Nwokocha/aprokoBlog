package africa.semicolon.aprokoBlog.dto.requests;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String username;
    private String title;
    private String content;
}