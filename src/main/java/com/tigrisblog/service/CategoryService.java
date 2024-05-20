package com.tigrisblog.service;

import com.tigrisblog.modal.CategoryRequest;
import com.tigrisblog.modal.CategoryResponse;

import java.util.List;

public interface CategoryService {

  List<CategoryResponse> findAllCategories();
  void deleteCategoryById(Long id);

  CategoryResponse saveCategory(CategoryRequest categoryRequest);


  CategoryResponse getCategoryById(Long id);

}
