package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.*;
import africa.semicolon.aprokoBlog.data.repository.Posts;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.DeletePostResponse;
import africa.semicolon.aprokoBlog.dtos.responses.ViewPostResponse;
import africa.semicolon.aprokoBlog.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.aprokoBlog.utils.Mapper.*;


@Service
public class PostServicesImpl implements PostServices {
    @Autowired
    private Posts posts;
    @Autowired
    private UserServices userServices;
    @Autowired
    private ViewServices viewServices;

    @Override
    public Post createPostWith(CreatePostRequest createPostRequest) {
        Post newPost = map(createPostRequest);
        return posts.save(newPost);
    }

    @Override
    public Post editPostWith(EditPostRequest editPostRequest) {
        Post editedPost = map(editPostRequest);
        return posts.save(editedPost);
    }

    @Override
    public DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest) {
        Post foundPost = findPostBy(deletePostRequest.getId());
        posts.delete(foundPost);
        return mapDeletePostResponse(foundPost);
    }

    @Override
    public ViewPostResponse viewPost(ViewPostRequest viewPostRequest) {
        Post foundPost = findPostBy(viewPostRequest.getPostId());
        View newView = viewServices.addViewWith(viewPostRequest);
        foundPost.getViews().add(newView);
        posts.save(foundPost);
        User user = newView.getViewer();
        user.getPosts().removeIf(post -> post.getId().equals(viewPostRequest.getPostId()));
        user.getPosts().add(foundPost);
        userServices.save(user);
//        userServices.updatePostViews(new UpdatePostViewRequest(newView.getViewer(), foundPost));
        return mapViewPostResponse(newView);
    }

    private Post findPostBy(String id) {
        Optional<Post> foundPost = posts.findById(id);
        if (foundPost.isEmpty()) throw new PostNotFoundException("Post not found");
        return foundPost.get();
    }
}
