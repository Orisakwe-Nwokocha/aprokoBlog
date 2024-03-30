package africa.semicolon.aprokoBlog.utils;

import africa.semicolon.aprokoBlog.data.models.*;
import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.*;

import java.time.format.DateTimeFormatter;

public final class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(registerRequest.getPassword());
        user.setUsername(registerRequest.getUsername());
        return user;
    }
    public static RegisterUserResponse registerResponseMap(User user) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(user.getId());
        registerUserResponse.setUsername(user.getUsername());
        registerUserResponse.setDateTimeRegistered(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(user.getDateRegistered()));
        return registerUserResponse;
    }

    public static LoginUserResponse mapLoginResponse(User user) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        loginUserResponse.setId(user.getId());
        loginUserResponse.setUsername(user.getUsername());
        return loginUserResponse;
    }

    public static LogoutUserResponse mapLogoutResponse(User user) {
        LogoutUserResponse logoutUserResponse = new LogoutUserResponse();
        logoutUserResponse.setId(user.getId());
        logoutUserResponse.setUsername(user.getUsername());
        return logoutUserResponse;
    }

    public static Post map(CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setTitle(createPostRequest.getTitle());
        post.setContent(createPostRequest.getContent());
        return post;
    }

    public static CreatePostResponse mapCreatePostResponse(Post post) {
        CreatePostResponse createPostResponse = new CreatePostResponse();
        createPostResponse.setPostId(post.getId());
        createPostResponse.setTitle(post.getTitle());
        createPostResponse.setContent(post.getContent());
        createPostResponse.setDateCreated(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(post.getDateCreated()));
        return createPostResponse;
    }

    public static Post map(EditPostRequest editPostRequest, Post oldPost) {
        Post post = new Post();
        post.setId(editPostRequest.getPostId());
        post.setTitle(editPostRequest.getTitle());
        post.setContent(editPostRequest.getContent());
        post.setDateCreated(oldPost.getDateCreated());
        return post;
    }

    public static EditPostResponse mapEditPostResponse(Post post) {
        EditPostResponse editPostResponse = new EditPostResponse();
        editPostResponse.setPostId(post.getId());
        editPostResponse.setTitle(post.getTitle());
        editPostResponse.setContent(post.getContent());
        editPostResponse.setDateCreated(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(post.getDateCreated()));
        return editPostResponse;
    }

    public static DeletePostResponse mapDeletePostResponse(Post post) {
        DeletePostResponse deletePostResponse = new DeletePostResponse();
        deletePostResponse.setPostId(post.getId());
        return deletePostResponse;
    }

    public static View map(User viewer) {
        View view = new View();
        view.setViewer(viewer);
        return view;
    }

    public static ViewPostResponse mapViewPostResponse(View view, Post post) {
        ViewPostResponse viewPostResponse = new ViewPostResponse();
        viewPostResponse.setPost(post);
        viewPostResponse.setViewerId(view.getViewer().getId());
        viewPostResponse.setViewer(view.getViewer().getUsername());
        viewPostResponse.setTimeOfView(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(view.getTimeOfView()));
        return viewPostResponse;
    }

    public static ViewPostRequest map(CommentRequest commentRequest) {
        ViewPostRequest viewPostRequest = new ViewPostRequest();
        viewPostRequest.setPostAuthor(commentRequest.getPostAuthor());
        viewPostRequest.setPostId(commentRequest.getPostId());
        viewPostRequest.setViewer(commentRequest.getCommenter());
        return viewPostRequest;
    }

    public static Comment map(CommentRequest commentRequest, User commenter) {
        Comment comment = new Comment();
        comment.setCommenter(commenter);
        comment.setComment(commentRequest.getComment());
        return comment;
    }

    public static CommentResponse mapCommentResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommenterId(comment.getCommenter().getId());
        commentResponse.setCommenter(comment.getCommenter().getUsername());
        return commentResponse;
    }

    public static GetUserPostsResponse mapGetUserPostsResponse(User user) {
        GetUserPostsResponse getUserPostsResponse = new GetUserPostsResponse();
        getUserPostsResponse.setUserId(user.getId());
        getUserPostsResponse.setUsername(user.getUsername());
        getUserPostsResponse.setUserPosts(user.getPosts());
        return getUserPostsResponse;
    }
}
