package africa.semicolon.aprokoBlog.dtos.responses;

import lombok.Data;

@Data
public class ViewPostResponse {
    private String viewerId;
    private String viewer;
    private String timeOfView;
}