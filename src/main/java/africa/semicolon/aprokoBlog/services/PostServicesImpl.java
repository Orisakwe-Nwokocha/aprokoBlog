package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.repository.Posts;
import africa.semicolon.aprokoBlog.dtos.requests.CreatePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.DeletePostRequest;
import africa.semicolon.aprokoBlog.dtos.requests.EditPostRequest;
import africa.semicolon.aprokoBlog.dtos.responses.DeletePostResponse;
import africa.semicolon.aprokoBlog.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.aprokoBlog.utils.Mapper.mapDeletePostResponse;
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

    @Override
    public Post editPost(EditPostRequest editPostRequest) {
        Post editedPost = map(editPostRequest);
        return posts.save(editedPost);
    }

    @Override
    public DeletePostResponse deletePost(DeletePostRequest deletePostRequest) {
        Post foundPost = findPostBy(deletePostRequest.getId());
        posts.delete(foundPost);
        return mapDeletePostResponse(foundPost);
    }

    private Post findPostBy(String id) {
        Optional<Post> foundPost = posts.findById(id);
        if (foundPost.isEmpty()) throw new PostNotFoundException("Post not found");
        return foundPost.get();
    }
}
