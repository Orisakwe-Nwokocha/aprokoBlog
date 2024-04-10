package africa.semicolon.aprokoBlog.dto.responses;

import lombok.Data;

@Data
public class CreatePostResponse {
    private String postId;
    private String title;
    private String content;
    private String dateCreated;
}