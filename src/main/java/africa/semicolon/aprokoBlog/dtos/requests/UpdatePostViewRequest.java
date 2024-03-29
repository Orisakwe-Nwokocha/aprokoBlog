package africa.semicolon.aprokoBlog.dtos.requests;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class UpdatePostViewRequest {
    private User user;
    private Post post;
}
