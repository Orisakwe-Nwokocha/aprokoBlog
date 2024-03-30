package africa.semicolon.aprokoBlog.dtos.responses;

import africa.semicolon.aprokoBlog.data.models.Post;
import lombok.Data;

import java.util.List;

@Data
public class GetUserPostsResponse {
    private String userId;
    private String username;
    private List<Post> userPosts;
}
