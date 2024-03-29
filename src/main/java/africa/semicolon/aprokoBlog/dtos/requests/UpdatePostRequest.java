package africa.semicolon.aprokoBlog.dtos.requests;

import africa.semicolon.aprokoBlog.data.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class UpdatePostRequest {
    private String postAuthor;
    private Post post;
}