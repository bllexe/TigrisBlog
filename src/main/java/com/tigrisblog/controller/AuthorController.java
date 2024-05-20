package com.tigrisblog.controller;

import com.tigrisblog.modal.AuthorRequest;
import com.tigrisblog.modal.AuthorResponse;
import com.tigrisblog.service.AuthorService;
import com.tigrisblog.utils.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.AuthorCTRL.CTRL)
public class AuthorController {

  private final AuthorService authorService;

  @PostMapping
  ResponseEntity<AuthorResponse> saveAuthor(@RequestBody AuthorRequest authorRequest){
    return new ResponseEntity<>(authorService.saveAuthor(authorRequest),HttpStatus.CREATED);
  }

  @GetMapping()
  ResponseEntity<List<AuthorResponse>> findAllAuthors(){
    return new ResponseEntity<>(authorService.findAllAuthors(),HttpStatus.OK);
  }

  @GetMapping("/{id}")
  ResponseEntity<AuthorResponse> findAuthorById(@PathVariable Long id){
    return new ResponseEntity<>(authorService.findAuthorById(id),HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteAuthorById(@PathVariable Long id){
    authorService.deleteAuthorById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  ResponseEntity<AuthorResponse> updateAuthorById(@PathVariable Long id, @RequestBody AuthorRequest authorRequest){
    return new ResponseEntity<>(authorService.updateAuthorById(id,authorRequest),HttpStatus.OK);
  }

}
