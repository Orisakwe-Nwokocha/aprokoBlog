package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.dtos.requests.UpdatePostRequest;

public interface UserServiceFacade {
    User findUserBy(String username);
    void updateUserPostWith(UpdatePostRequest updatePostRequest);
}
