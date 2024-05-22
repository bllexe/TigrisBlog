package com.tigrisblog.controller;

import com.tigrisblog.modal.ArticleRequest;
import com.tigrisblog.modal.ArticleResponse;
import com.tigrisblog.service.ArticleService;
import com.tigrisblog.utils.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ArticleCTRL.CTRL)
public class ArticleController {

  private final ArticleService articleService;

  @PostMapping
  public ResponseEntity<ArticleResponse> saveArticle(@RequestBody ArticleRequest articleRequest) {
    return new ResponseEntity<>(articleService.saveArticle(articleRequest), HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<ArticleResponse> findArticleById(@PathVariable Long id){
    return new ResponseEntity<>(articleService.findArticleById(id),HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<ArticleResponse>> findAllArticles(){
    return new ResponseEntity<>(articleService.findAllArticles(),HttpStatus.OK);
  }

  @GetMapping("{categoryId}")
  public ResponseEntity<List<ArticleResponse>> findArticleByCategory(@PathVariable Long categoryId){
    return new ResponseEntity<>(articleService.findArticleByCategory(categoryId),HttpStatus.OK);
  }
  @GetMapping("{authorId}")
  public ResponseEntity<List<ArticleResponse>> findArticleByAuthor(@PathVariable Long authorId){
    return new ResponseEntity<>(articleService.findArticleByAuthor(authorId),HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteArticleById(@PathVariable Long id){
    articleService.deleteArticleById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("{id}")
  public ResponseEntity<ArticleResponse> updateArticleById(@PathVariable Long id, @RequestBody ArticleRequest articleRequest){
    return new ResponseEntity<>(articleService.updateArticleById(id, articleRequest),HttpStatus.OK);
  }

}
