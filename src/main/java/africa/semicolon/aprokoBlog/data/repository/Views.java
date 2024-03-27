package africa.semicolon.aprokoBlog.data.repository;

import africa.semicolon.aprokoBlog.data.models.View;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Views extends MongoRepository<View, String> {
}
