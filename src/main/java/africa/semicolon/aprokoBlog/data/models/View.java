package africa.semicolon.aprokoBlog.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Document("Views")
public class View {
    @Id
    private String id;
    @DBRef
    private User viewer;
    private LocalDateTime timeOfView = LocalDateTime.now();

    @Override
    public String toString() {
        String viewedTime = DateTimeFormatter.ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(timeOfView);
        return String.format("""
                id='%s'
                timeOfView='%s'""", id, viewedTime);
    }

}