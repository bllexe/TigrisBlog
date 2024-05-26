package com.tigrisblog.service.impl;

import com.tigrisblog.entity.Article;
import com.tigrisblog.entity.Author;
import com.tigrisblog.entity.Category;
import com.tigrisblog.entity.Image;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private final ArticleRepository articleRepository;
  private final AuthorRepository authorRepository;
  private final CategoryRepository categoryRepository;
  private final ImageUploadService imageUploadService;

  @Override
  public ArticleResponse saveArticle(ArticleRequest articleRequest) {

    Author authorDb = authorRepository.findById(articleRequest.authorId()).orElseThrow(() -> new IllegalArgumentException("Author not found"));
    List<Category> categories = categoryRepository.findAllById(articleRequest.categoryIds());

    String imageUrl = imageUploadService.uploadFile(articleRequest.multipartFile());

    Article article = new Article();
    article.setTitle(articleRequest.title());
    article.setContent(articleRequest.content());
    article.setAuthor(authorDb);
    article.setCategories(categories);

    Image image = new Image();
    image.setUrl(imageUrl);
    image.setArticle(article);
    article.setImage(image);

    Article savedArticle = articleRepository.save(article);

    return new ArticleResponse(
      savedArticle.getId(),
      savedArticle.getTitle(),
      savedArticle.getContent(),
      savedArticle.getCreated_at(),
      savedArticle.getUpdated_at(),
      savedArticle.getAuthor(),
      savedArticle.getCategories(),
      imageUrl
    );
  }

  @Override
  public ArticleResponse findArticleById(Long id) {
    Article dbArticle = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Article not found"));

    String imageUrl = dbArticle.getImage().getUrl();
    return new ArticleResponse(
      dbArticle.getId(),
      dbArticle.getTitle(),
      dbArticle.getContent(),
      dbArticle.getCreated_at(),
      dbArticle.getUpdated_at(),
      dbArticle.getAuthor(),
      dbArticle.getCategories(),
      imageUrl
    );

  }

  @Override
  public List<ArticleResponse> findAllArticles() {
    List<Article> articles = articleRepository.findAll();

    // ArticleResponse listesi oluştur
    List<ArticleResponse> articleResponses = new ArrayList<>();

    // Her makale için ArticleResponse oluştur ve listeye ekle
    for (Article article : articles) {
      // Makaleye ait resmi al
      String imageUrl = article.getImage().getUrl();

      // ArticleResponse oluştur
      ArticleResponse articleResponse = new ArticleResponse(
        article.getId(),
        article.getTitle(),
        article.getContent(),
        article.getCreated_at(),
        article.getUpdated_at(),
        article.getAuthor(),
        article.getCategories(),
        imageUrl // Resim URL'si
      );

      // ArticleResponse'ı listeye ekle
      articleResponses.add(articleResponse);
    }

    // Tüm ArticleResponse'ları döndür
    return articleResponses;


  }

  @Override
  public List<ArticleResponse> findArticleByCategory(Long categoryId) {
    List<Article> articles = articleRepository.findArticlesByCategoriesId(categoryId);

    List<ArticleResponse> articleResponses = new ArrayList<>();

    for (Article article : articles) {
      String imageUrl = article.getImage().getUrl();

      ArticleResponse articleResponse = new ArticleResponse(
        article.getId(),
        article.getTitle(),
        article.getContent(),
        article.getCreated_at(),
        article.getUpdated_at(),
        article.getAuthor(),
        article.getCategories(),
        imageUrl
      );
      articleResponses.add(articleResponse);
    }
    return articleResponses;
  }


  @Override
  public List<ArticleResponse> findArticleByAuthor(Long authorId) {
    List<Article> articles = articleRepository.findArticlesByAuthorId(authorId);

    List<ArticleResponse> articleResponses = new ArrayList<>();

    for (Article article : articles) {
      String imageUrl = article.getImage().getUrl();

      ArticleResponse articleResponse = new ArticleResponse(
        article.getId(),
        article.getTitle(),
        article.getContent(),
        article.getCreated_at(),
        article.getUpdated_at(),
        article.getAuthor(),
        article.getCategories(),
        imageUrl
      );
      articleResponses.add(articleResponse);
    }
    return articleResponses;

  }

  @Override
  public void deleteArticleById(Long id) {

    articleRepository.deleteById(id);
    imageUploadService.deleteFileFromS3(id);

  }

  @Override
  public ArticleResponse updateArticleById(Long id, ArticleRequest articleRequest) {
    Article dbArticle = articleRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Article not found"));

    dbArticle.setTitle(articleRequest.title());
    dbArticle.setContent(articleRequest.content());

    Author authorDb = authorRepository.findById(articleRequest.authorId())
      .orElseThrow(() -> new IllegalArgumentException("Author not found"));

    dbArticle.setAuthor(authorDb);

    List<Category> categories = categoryRepository.findAllById(articleRequest.categoryIds());
    dbArticle.setCategories(categories);

    if (articleRequest.multipartFile() != null) {
      String imageUrl = imageUploadService.uploadFile(articleRequest.multipartFile());
      Image image = dbArticle.getImage();
      if (image == null) {
        image = new Image();
        image.setArticle(dbArticle);
      }
      image.setUrl(imageUrl);
      dbArticle.setImage(image);
    }
    Article updatedArticle = articleRepository.save(dbArticle);
    String imageUrl = updatedArticle.getImage() != null ? updatedArticle.getImage().getUrl() : null;
    return new ArticleResponse(
      updatedArticle.getId(),
      updatedArticle.getTitle(),
      updatedArticle.getContent(),
      updatedArticle.getCreated_at(),
      updatedArticle.getUpdated_at(),
      updatedArticle.getAuthor(),
      updatedArticle.getCategories(),
      imageUrl
    );
  }
}
