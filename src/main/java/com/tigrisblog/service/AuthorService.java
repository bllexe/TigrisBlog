package com.tigrisblog.service;

import com.tigrisblog.entity.Author;
import com.tigrisblog.modal.AuthorRequest;
import com.tigrisblog.modal.AuthorResponse;

import java.util.List;

public interface AuthorService {

  List<AuthorResponse> findAllAuthors();
  AuthorResponse findAuthorById(Long id);

  AuthorResponse saveAuthor(AuthorRequest authorRequest);

  void deleteAuthorById(Long id);

  AuthorResponse updateAuthorById(Long id, AuthorRequest authorRequest);
}
