package com.tigrisblog.modal;

import java.util.List;

public record ArticleRequest(String title, String content,
                             String imageUrl, long authorId,
                             List<Long> categoryIds) {


}
