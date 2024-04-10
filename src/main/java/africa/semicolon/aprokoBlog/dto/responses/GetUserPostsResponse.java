package africa.semicolon.aprokoBlog.dto.responses;

import lombok.Data;

@Data
public class GetUserPostsResponse {
    private String userId;
    private String username;
    private String userPosts;
}
