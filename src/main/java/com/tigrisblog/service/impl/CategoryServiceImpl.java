package com.tigrisblog.service.impl;

import com.tigrisblog.entity.Category;
import com.tigrisblog.modal.AuthorResponse;
import com.tigrisblog.modal.CategoryRequest;
import com.tigrisblog.modal.CategoryResponse;
import com.tigrisblog.repos.CategoryRepository;
import com.tigrisblog.service.AuthorService;
import com.tigrisblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  @Override
  public List<CategoryResponse> findAllCategories() {
    List<Category> allCategory = categoryRepository.findAll();
    return allCategory.stream().map(category -> new CategoryResponse(category.getId(),category.getTitle()))
      .collect(Collectors.toList());
  }

  @Override
  public void deleteCategoryById(Long id) {
      categoryRepository.deleteById(id);
  }

  @Override
  public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
    Category category = new Category();
    category.setTitle(categoryRequest.title());
    Category savedCategory = categoryRepository.save(category);
    return new CategoryResponse(savedCategory.getId(), savedCategory.getTitle());
  }

  @Override
  public CategoryResponse getCategoryById(Long id) {
    Category categoryDb = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    return new CategoryResponse(categoryDb.getId(), categoryDb.getTitle());
  }
}
