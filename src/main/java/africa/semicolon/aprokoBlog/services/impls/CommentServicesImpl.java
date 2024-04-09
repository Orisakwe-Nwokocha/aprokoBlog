package africa.semicolon.aprokoBlog.services.impls;

import africa.semicolon.aprokoBlog.data.models.Comment;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.repository.Comments;
import africa.semicolon.aprokoBlog.dtos.requests.CommentRequest;
import africa.semicolon.aprokoBlog.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Mapper.map;

@Service
public class CommentServicesImpl implements CommentServices {
    @Autowired
    private Comments comments;

    @Override
    public Comment addCommentWith(CommentRequest commentRequest, User commenter) {
        Comment newComment = map(commentRequest, commenter);
        return comments.save(newComment);
    }
}
