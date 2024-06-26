package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Comment;
import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dto.requests.CommentRequest;

public interface CommentServices {
    Comment addCommentWith(CommentRequest commentRequest, User commenter);
}
