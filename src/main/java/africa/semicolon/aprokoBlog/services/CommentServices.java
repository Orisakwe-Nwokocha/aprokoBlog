package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.Comment;
import africa.semicolon.aprokoBlog.dtos.requests.CommentRequest;

public interface CommentServices {
    Comment addCommentWith(CommentRequest commentRequest);
}
