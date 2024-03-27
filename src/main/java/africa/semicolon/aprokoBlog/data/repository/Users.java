package africa.semicolon.aprokoBlog.data.repository;

import africa.semicolon.aprokoBlog.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Users extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
}
