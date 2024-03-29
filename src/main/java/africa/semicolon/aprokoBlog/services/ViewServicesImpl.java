package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.models.View;
import africa.semicolon.aprokoBlog.data.repository.Views;
import africa.semicolon.aprokoBlog.dtos.requests.ViewPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Mapper.map;

@Service
public class ViewServicesImpl implements ViewServices {
    @Autowired
    private Views views;
    @Autowired
    private UserServiceFacade userServiceFacade;

    @Override
    public View addViewWith(ViewPostRequest viewPostRequest) {
        User viewer = userServiceFacade.findUserBy(viewPostRequest.getViewer());
        View newView = map(viewPostRequest, viewer);
        return views.save(newView);
    }
}
