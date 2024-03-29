package africa.semicolon.aprokoBlog.dtos.responses;

import lombok.Data;

@Data
public class ViewPostResponse {
    private String id;
    private String viewerId;
    private String viewerUsername;
    private String timeOfView;
}
