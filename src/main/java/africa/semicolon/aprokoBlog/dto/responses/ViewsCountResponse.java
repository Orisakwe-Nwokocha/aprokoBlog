package africa.semicolon.aprokoBlog.dto.responses;

import lombok.Data;

@Data
public class ViewsCountResponse {
    private String postId;
    private Long viewsCount;
}
