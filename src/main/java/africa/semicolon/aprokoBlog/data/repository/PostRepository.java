package africa.semicolon.aprokoBlog.data.repository;

import africa.semicolon.aprokoBlog.data.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends MongoRepository<Post, String> {
}
