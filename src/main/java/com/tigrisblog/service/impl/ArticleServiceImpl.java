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
    //todo create custom exception

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
    Article dbArticle = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Article not found"));

      return new ArticleResponse(dbArticle.getId(), dbArticle.getTitle(), dbArticle.getContent(),
      dbArticle.getCreated_at(), dbArticle.getUpdated_at(),
      dbArticle.getImageUrl(), dbArticle.getAuthor().getId(),
      dbArticle.getCategories().stream().map(Category::getId).collect(Collectors.toList()));
  }

  @Override
  public List<ArticleResponse> findAllArticles() {

    List<Article> allArticles = articleRepository.findAll();
    return allArticles.stream()
      .map(article -> new ArticleResponse(article.getId(), article.getTitle(), article.getContent(),
        article.getCreated_at(), article.getUpdated_at(),
        article.getImageUrl(), article.getAuthor().getId(),
        article.getCategories().stream().map(Category::getId).collect(Collectors.toList())))
      .collect(Collectors.toList());
  }

  @Override
  public List<ArticleResponse> findArticleByCategory(Long categoryId) {

    List<Article> allArticles = articleRepository.findArticlesByCategoriesId(categoryId);
    return allArticles.stream()
      .map(article -> new ArticleResponse(article.getId(), article.getTitle(), article.getContent(),
        article.getCreated_at(), article.getUpdated_at(),
        article.getImageUrl(), article.getAuthor().getId(),
        article.getCategories().stream().map(Category::getId).collect(Collectors.toList())))
      .collect(Collectors.toList());

  }

  @Override
  public List<ArticleResponse> findArticleByAuthor(Long authorId) {

    List<Article> allArticles = articleRepository.findArticlesByAuthorId(authorId);
    return allArticles.stream()
      .map(article -> new ArticleResponse(article.getId(), article.getTitle(), article.getContent(),
        article.getCreated_at(), article.getUpdated_at(),
        article.getImageUrl(), article.getAuthor().getId(),
        article.getCategories().stream().map(Category::getId).collect(Collectors.toList())))
      .collect(Collectors.toList());
  }

  @Override
  public void deleteArticleById(Long id) {
   categoryRepository.deleteById(id);

  }

  @Override
  public ArticleResponse updateArticleById(Long id, ArticleRequest articleRequest) {

    Article dbArticle = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Article not found"));
    dbArticle.setTitle(articleRequest.title());
    dbArticle.setContent(articleRequest.content());
    dbArticle.setImageUrl(articleRequest.imageUrl());
    dbArticle.setUpdated_at(LocalDate.now().toString());
    Article updatedArticle = articleRepository.save(dbArticle);
    return new ArticleResponse(updatedArticle.getId(), updatedArticle.getTitle(), updatedArticle.getContent(),
      updatedArticle.getCreated_at(), updatedArticle.getUpdated_at(),
      updatedArticle.getImageUrl(), updatedArticle.getAuthor().getId(),
      updatedArticle.getCategories().stream().map(Category::getId).collect(Collectors.toList()));
  }
}
