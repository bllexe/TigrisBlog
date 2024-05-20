package com.tigrisblog.service.impl;

import com.tigrisblog.entity.Article;
import com.tigrisblog.entity.Author;
import com.tigrisblog.entity.Category;
import com.tigrisblog.modal.ArticleRequest;
import com.tigrisblog.modal.ArticleResponse;
import com.tigrisblog.modal.CategoryResponse;
import com.tigrisblog.repos.ArticleRepository;
import com.tigrisblog.repos.AuthorRepository;
import com.tigrisblog.repos.CategoryRepository;
import com.tigrisblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private final ArticleRepository articleRepository;
  private final AuthorRepository authorRepository;
  private final CategoryRepository categoryRepository;

  @Override
  public ArticleResponse saveArticle(ArticleRequest articleRequest) {
    Article article = new Article();

    Author author = authorRepository.findById(articleRequest.authorId())
      .orElseThrow(() -> new IllegalArgumentException("Author not found"));

    List<Category> categories = categoryRepository.findAllById(articleRequest.categoryIds());

    article.setTitle(articleRequest.title());
    article.setContent(articleRequest.content());
    article.setImageUrl(articleRequest.imageUrl());
    article.setAuthor(author);
    article.setCreated_at(LocalDate.now().toString());
    article.setCategories(categories);

    Article savedArticle = articleRepository.save(article);

    return new ArticleResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent(),
      savedArticle.getCreated_at(), savedArticle.getUpdated_at(),
      savedArticle.getImageUrl(), savedArticle.getAuthor().getId(),
      savedArticle.getCategories().stream().map(Category::getId).collect(Collectors.toList()));
  }

  @Override
  public ArticleResponse findArticleById(Long id) {
    return null;
  }

  @Override
  public List<ArticleResponse> findAllArticles() {
    return null;
  }

  @Override
  public List<CategoryResponse> findArticleByCategory(Long categoryId) {
    return null;
  }

  @Override
  public List<ArticleResponse> findArticleByAuthor(Long authorId) {
    return null;
  }

  @Override
  public void deleteArticleById(Long id) {

  }

  @Override
  public ArticleResponse updateArticleById(Long id, ArticleRequest articleRequest) {
    return null;
  }
}
