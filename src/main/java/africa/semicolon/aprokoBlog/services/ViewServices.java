package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.User;
import africa.semicolon.aprokoBlog.data.models.View;

public interface ViewServices {
    View saveViewOf(User viewer);
}
