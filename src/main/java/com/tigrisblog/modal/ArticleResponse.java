package com.tigrisblog.modal;

import com.tigrisblog.entity.Author;
import com.tigrisblog.entity.Category;

import java.util.List;

public record ArticleResponse(long id, String title, String content,
                              String created_at,String updated_at,
                               Author author,
                              List<Category> categories,
                              String imageUrl) {
}
