package africa.semicolon.aprokoBlog.dtos.responses;

import lombok.Data;

@Data
public class ViewsCountResponse {
    private String postId;
    private Long viewsCount;
}
