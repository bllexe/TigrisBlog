package com.tigrisblog.modal;

import java.util.List;

public record ArticleResponse(long id, String title, String content,
                              String created_at,String updated_at,
                              String imageUrl, long authorId,
                              List<Long> categoryIds) {


}
