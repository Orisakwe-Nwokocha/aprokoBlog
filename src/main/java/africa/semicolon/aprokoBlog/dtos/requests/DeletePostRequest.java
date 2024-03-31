package africa.semicolon.aprokoBlog.dtos.requests;

import lombok.Data;

@Data
public class DeletePostRequest {
    private String postId;
    private String author;
}