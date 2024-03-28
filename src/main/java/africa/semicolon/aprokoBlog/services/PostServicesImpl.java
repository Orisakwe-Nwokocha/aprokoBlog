package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.repository.Posts;
import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Mapper.map;

@Service
public class PostServicesImpl implements PostServices {
    @Autowired
    private Posts posts;

    @Override
    public Post createPost(CreatePostRequest createPostRequest) {
        Post newPost = map(createPostRequest);
        return posts.save(newPost);
    }
}
