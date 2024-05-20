package com.tigrisblog.modal;

import java.util.List;

public record AuthorResponse(long id, String name, String username, String email, String bio, List<Long> articleIds) {

}
