package africa.semicolon.aprokoBlog.services;

import africa.semicolon.aprokoBlog.data.models.View;
import africa.semicolon.aprokoBlog.dtos.requests.ViewPostRequest;

public interface ViewServices {
    View addViewWith(ViewPostRequest viewPostRequest);
}
