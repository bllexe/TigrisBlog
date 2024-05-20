package com.tigrisblog.controller;

import com.tigrisblog.modal.CategoryRequest;
import com.tigrisblog.modal.CategoryResponse;
import com.tigrisblog.service.CategoryService;
import com.tigrisblog.utils.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.CategoryCTRL.CTRL)
public class CategoryController {

  private  final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest){
    return new ResponseEntity<>(categoryService.saveCategory(categoryRequest), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
    categoryService.deleteCategoryById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> findAllCategories(){
    return new ResponseEntity<>(categoryService.findAllCategories(),HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id){
    return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);
  }


}
