package africa.semicolon.aprokoBlog.dtos.requests;

import lombok.Data;

@Data
public class EditPostRequest {
    private String id;
    private String username;
    private String title;
    private String content;
}
