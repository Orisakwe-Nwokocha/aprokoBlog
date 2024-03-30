package africa.semicolon.aprokoBlog.controllers;

import africa.semicolon.aprokoBlog.dtos.requests.*;
import africa.semicolon.aprokoBlog.dtos.responses.ApiResponse;
import africa.semicolon.aprokoBlog.exceptions.AprokoBlogAppException;
import africa.semicolon.aprokoBlog.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class PostControllers {
    @Autowired
    private PostServices postServices;

    @PutMapping("/edit-post")
    public ResponseEntity<?> editPost(@RequestBody EditPostRequest editPostRequest) {
        try {
            var result = postServices.editPostWith(editPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<?> deletePost(@RequestBody DeletePostRequest deletePostRequest) {
        try {
            var result = postServices.deletePostWith(deletePostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/number-of-views")
    public ResponseEntity<?> getViewsCount(@RequestBody ViewsCountRequest viewsCountRequest) {
        try {
            var result = postServices.getNumberOfViews(viewsCountRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        } catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}