package africa.semicolon.aprokoBlog.controllers;

import africa.semicolon.aprokoBlog.dto.requests.*;
import africa.semicolon.aprokoBlog.dto.responses.ApiResponse;
import africa.semicolon.aprokoBlog.exceptions.AprokoBlogAppException;
import africa.semicolon.aprokoBlog.services.PostServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class PostControllers {
    @Autowired
    private PostServices postServices;

    @GetMapping("/number-of-views")
    public ResponseEntity<?> getViewsCount(@Valid @RequestBody ViewsCountRequest viewsCountRequest) {
        try {
            var result = postServices.getNumberOfViews(viewsCountRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        } catch (AprokoBlogAppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}