package com.tigrisblog.repos;

import com.tigrisblog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findArticlesByCategoriesId(Long categoryId);

  List<Article> findArticlesByAuthorId(Long authorId);

}
