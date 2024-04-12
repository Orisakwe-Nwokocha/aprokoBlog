package africa.semicolon.aprokoBlog.controllers;

import africa.semicolon.aprokoBlog.dto.requests.*;
import africa.semicolon.aprokoBlog.dto.responses.ApiResponse;
import africa.semicolon.aprokoBlog.exceptions.AprokoBlogAppException;
import africa.semicolon.aprokoBlog.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class UserControllers {
    @Autowired
    private UserServices userServices;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            var result = userServices.register(registerRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            var result = userServices.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        try {
            var result = userServices.logout(logoutRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        try {
            var result = userServices.createPost(createPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PutMapping("/edit-post")
    public ResponseEntity<?> editPost(@Valid @RequestBody EditPostRequest editPostRequest) {
        try {
            var result = userServices.editPostWith(editPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<?> deletePost(@Valid @RequestBody DeletePostRequest deletePostRequest) {
        try {
            var result = userServices.deletePostWith(deletePostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/view-post")
    public ResponseEntity<?> viewPost(@Valid @RequestBody ViewPostRequest viewPostRequest) {
        try {
            var result = userServices.viewPost(viewPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        try {
            var result = userServices.reactToPost(commentRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/view-posts")
    public ResponseEntity<?> getUserPosts(@Valid @RequestBody GetUserPostsRequest getUserPostsRequest) {
        try {
            var result = userServices.getUserPosts(getUserPostsRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}