package africa.semicolon.aprokoBlog.data.repository;

import africa.semicolon.aprokoBlog.data.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Comments extends MongoRepository<Comment, String> {
}
