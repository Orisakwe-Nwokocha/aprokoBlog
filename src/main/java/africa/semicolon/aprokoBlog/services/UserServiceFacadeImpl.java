package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Post;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.repository.Users;
import africa.semicolon.aprokoBlog.dtos.requests.UpdatePostRequest;
import africa.semicolon.aprokoBlog.exceptions.PostNotFoundException;
import africa.semicolon.aprokoBlog.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Cleaner.cleanup;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {
    @Autowired
    private Users users;

    @Override
    public void updateUserPostWith(UpdatePostRequest updatePostRequest) {
        User user = findUserBy(updatePostRequest.getPostAuthor());
        Post updatedPost = updatePostRequest.getPost();
        Post oldPost = findUserPostBy(updatedPost.getId(), user);
        user.getPosts().remove(oldPost);
        user.getPosts().add(updatedPost);
        users.save(user);
    }

    private Post findUserPostBy(String id, User user) {
        for (Post post : user.getPosts()) if (post.getId().equals(id)) return post;
        throw new PostNotFoundException("Post not found");
    }

    @Override
    public User findUserBy(String username) {
        username = cleanup(username);
        User foundUser = users.findByUsername(username);
        if (foundUser == null) throw new UsernameNotFoundException(String.format("%s not found", username));
        return foundUser;
    }
}
