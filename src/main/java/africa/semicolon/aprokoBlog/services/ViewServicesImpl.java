package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.models.View;
import africa.semicolon.aprokoBlog.data.repository.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.aprokoBlog.utils.Mapper.map;

@Service
public class ViewServicesImpl implements ViewServices {
    @Autowired
    private Views views;

    @Override
    public View saveViewOf(User viewer) {
        View newView = map(viewer);
        return views.save(newView);
    }
}
