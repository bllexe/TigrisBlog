package com.tigrisblog.service.impl;

import com.tigrisblog.entity.Article;
import com.tigrisblog.entity.Author;
import com.tigrisblog.modal.AuthorRequest;
import com.tigrisblog.modal.AuthorResponse;
import com.tigrisblog.repos.AuthorRepository;
import com.tigrisblog.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public List<AuthorResponse> findAllAuthors() {
    List<Author> allAuthors = authorRepository.findAll();
    return allAuthors.stream()
      .map(author -> new AuthorResponse(author.getId(), author.getName(), author.getUsername(),
        author.getEmail(), author.getBio(),
        author.getArticles().stream().map(Article::getId).collect(Collectors.toList())))
      .collect(Collectors.toList());
  }

  @Override
  public AuthorResponse findAuthorById(Long id) {
    Author authorDb = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author not found"));
    return new AuthorResponse(authorDb.getId(), authorDb.getName(), authorDb.getUsername(),
      authorDb.getEmail(), authorDb.getBio(),
      authorDb.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
  }

  @Override
  public AuthorResponse saveAuthor(AuthorRequest authorRequest) {
    Author author=new Author();
    author.setName(authorRequest.name());
    author.setUsername(authorRequest.username());
    author.setEmail(authorRequest.email());
    author.setBio(authorRequest.bio());
    author.setArticles(null);
    Author savedAuthor = authorRepository.save(author);
    return new AuthorResponse(savedAuthor.getId(), savedAuthor.getName(), savedAuthor.getUsername(),
      savedAuthor.getEmail(), savedAuthor.getBio(),
      savedAuthor.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
  }

  @Override
  public void deleteAuthorById(Long id) {
       authorRepository.deleteById(id);
  }

  @Override
  public AuthorResponse updateAuthorById(Long id, AuthorRequest authorRequest) {
    Author authorDb = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author not found"));
    authorDb.setName(authorRequest.name());
    authorDb.setUsername(authorRequest.username());
    authorDb.setEmail(authorRequest.email());
    authorDb.setBio(authorRequest.bio());
    Author savedAuthor = authorRepository.save(authorDb);
    return new AuthorResponse(savedAuthor.getId(), savedAuthor.getName(), savedAuthor.getUsername(),
      savedAuthor.getEmail(), savedAuthor.getBio(),
      savedAuthor.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
  }
}
