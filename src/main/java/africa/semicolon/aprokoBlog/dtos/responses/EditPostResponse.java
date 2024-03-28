package africa.semicolon.aprokoBlog.dtos.responses;

import lombok.Data;

@Data
public class EditPostResponse {
    public String id;
    private String title;
    private String content;
    private String dateCreated;
}
