package africa.semicolon.aprokoBlog.data.repository;

import africa.semicolon.aprokoBlog.data.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Posts extends MongoRepository<Post, String> {
}
