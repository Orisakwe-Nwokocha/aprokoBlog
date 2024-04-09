package africa.semicolon.aprokoBlog.services.impls;

import africa.semicolon.aprokoBlog.data.models.Comment;
import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.models.View;
import africa.semicolon.aprokoBlog.data.repository.Posts;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.*;
import africa.semicolon.aprokoBlog.exceptions.PostNotFoundException;
import africa.semicolon.aprokoBlog.services.CommentServices;
import africa.semicolon.aprokoBlog.services.PostServices;
import africa.semicolon.aprokoBlog.services.ViewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.aprokoBlog.utils.Mapper.*;


@Service
public class PostServicesImpl implements PostServices {
    @Autowired
    private Posts posts;
    @Autowired
    private ViewServices viewServices;
    @Autowired
    private CommentServices commentServices;

    @Override
    public Post createPostWith(CreatePostRequest createPostRequest) {
        Post newPost = map(createPostRequest);
        return posts.save(newPost);
    }

    @Override
    public EditPostResponse editPostWith(EditPostRequest editPostRequest, Post authorPost) {
        Post editedPost = map(editPostRequest, authorPost);
        posts.save(editedPost);
        return mapEditPostResponseWith(editedPost);
    }

    @Override
    public DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest, Post authorPost) {
        posts.delete(authorPost);
        return mapDeletePostResponseWith(authorPost);
    }

    @Override
    public ViewPostResponse addViewWith(ViewPostRequest viewPostRequest, User viewer) {
        Post post = findPostBy(viewPostRequest.getPostId());
        View newView = viewServices.saveViewOf(viewer);
        post.getViews().add(newView);
        Post savedPost = posts.save(post);
        return mapViewPostResponseWith(newView, savedPost);
    }

    @Override
    public CommentResponse addCommentWith(CommentRequest commentRequest, User commenter) {
        Post post = findPostBy(commentRequest.getPostId());
        View newView = viewServices.saveViewOf(commenter);
        Comment newComment = commentServices.addCommentWith(commentRequest, commenter);
        post.getViews().add(newView);
        post.getComments().add(newComment);
        posts.save(post);
        return mapCommentResponse(newComment);
    }

    @Override
    public ViewsCountResponse getNumberOfViews(ViewsCountRequest viewsCountRequest) {
        Post foundPost = findPostBy(viewsCountRequest.getPostId());
        Long viewsCount = (long) foundPost.getViews().size();
        return map(viewsCount, viewsCountRequest.getPostId());
    }

    private Post findPostBy(String id) {
        Optional<Post> foundPost = posts.findById(id);
        if (foundPost.isEmpty()) throw new PostNotFoundException("Post not found");
        return foundPost.get();
    }
}