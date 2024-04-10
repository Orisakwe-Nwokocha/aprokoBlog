package africa.semicolon.aprokoBlog.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("Users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isLoggedIn = true;
    private LocalDateTime dateRegistered = LocalDateTime.now();
    @DBRef
    private List<Post> posts = new ArrayList<>();

    @Override
    public String toString() {
        String registrationDate = DateTimeFormatter.ofPattern("dd/MMM/yyyy 'at' hh:mm:ss a").format(dateRegistered);
        return String.format("""
                id='%s'
                firstName='%s'
                lastName='%s'
                username='%s'
                login status='%s'
                dateRegistered='%s'""", id, firstName, lastName, username, isLoggedIn, registrationDate);
    }

}