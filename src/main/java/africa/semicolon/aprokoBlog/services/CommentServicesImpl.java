package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Comment;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.repository.Comments;
import africa.semicolon.aprokoBlog.dtos.requests.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Mapper.map;

@Service
public class CommentServicesImpl implements CommentServices {
    @Autowired
    private Comments comments;
    @Autowired
    private UserServiceFacade userServiceFacade;
    @Override
    public Comment addCommentWith(CommentRequest commentRequest) {
        User commenter = userServiceFacade.findUserBy(commentRequest.getCommenter());
        Comment newComment = map(commentRequest, commenter);
        return comments.save(newComment);
    }
}
