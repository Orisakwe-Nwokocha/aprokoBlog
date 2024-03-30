package africa.semicolon.aprokoBlog.controllers;

import africa.semicolon.aprokoBlog.dtos.requests.CommentRequest;
import africa.semicolon.aprokoBlog.dtos.requests.ViewPostRequest;
import africa.semicolon.aprokoBlog.dtos.responses.ApiResponse;
import africa.semicolon.aprokoBlog.exceptions.AprokoBlogAppException;
import africa.semicolon.aprokoBlog.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class PostControllers {
    @Autowired
    private PostServices postServices;

    @PatchMapping("/view-post")
    public ResponseEntity<?> getPost(@RequestBody ViewPostRequest viewPostRequest) {
        try {
            var result = postServices.viewPost(viewPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PatchMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest) {
        try {
            var result = postServices.addComment(commentRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}