package com.tigrisblog.service;

import com.tigrisblog.modal.ArticleRequest;
import com.tigrisblog.modal.ArticleResponse;
import com.tigrisblog.modal.CategoryResponse;

import java.util.List;

public interface ArticleService {

  ArticleResponse saveArticle(ArticleRequest articleRequest);

  ArticleResponse findArticleById(Long id);

  List<ArticleResponse> findAllArticles();

 List<CategoryResponse> findArticleByCategory(Long categoryId);

 List<ArticleResponse> findArticleByAuthor(Long authorId);

 void deleteArticleById(Long id);

 ArticleResponse updateArticleById(Long id, ArticleRequest articleRequest);

}
