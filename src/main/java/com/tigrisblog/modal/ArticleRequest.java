package com.tigrisblog.modal;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ArticleRequest(String title, String content,
                             long authorId,
                             List<Long> categoryIds,
                             MultipartFile multipartFile) {


}
