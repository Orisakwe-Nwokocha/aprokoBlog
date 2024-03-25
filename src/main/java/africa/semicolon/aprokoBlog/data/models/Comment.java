package africa.semicolon.aprokoBlog.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Comments")
public class Comment {
    @Id
    private String id;
    private User commenter;
    private String comment;
}
