package com.vnpt.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnpt.category.entity.Category;
import com.vnpt.category.model.CategoryModel;
import com.vnpt.category.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<CategoryModel> findCategoryByCategoryName(String categoryName) {
		List<Category> listCategory = categoryRepository.findAllByCategoryName(categoryName != null ? categoryName : "");
		List<CategoryModel> listCategoryModel = new ArrayList<>();
		listCategory.forEach(item->{
			CategoryModel categoryModel = new CategoryModel(); 
			categoryModel.setCategoryId(item.getCategoryId());
			categoryModel.setCategoryName(item.getCategoryName());
			categoryModel.setDescription(item.getDescription());
			categoryModel.setUrl(item.getUrl());
			categoryModel.setParentId(item.getParentId());
			listCategoryModel.add(categoryModel);
		});
		return listCategoryModel;
	}
	
	public CategoryModel getCategoryById(Long categoryId) {
		Category category = categoryRepository.findOneByCategoryId(categoryId);
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryId(category.getCategoryId());
		categoryModel.setCategoryName(category.getCategoryName());
		categoryModel.setDescription(category.getDescription());
		categoryModel.setUrl(category.getUrl());
		categoryModel.setParentId(category.getParentId());
		return categoryModel;
	}
	
	public String addCategory(CategoryModel categoryModel) {
		Category categoryEntity = new Category();
		categoryEntity.setCategoryName(categoryModel.getCategoryName());
		categoryEntity.setDescription(categoryModel.getDescription());
		categoryEntity.setUrl(categoryModel.getUrl());
		categoryEntity.setParentId(categoryModel.getParentId());
		categoryRepository.save(categoryEntity);
		return "Thêm mới danh mục thành công";
	}
	
	public String deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
		return "Xóa danh mục thành công";
	}
	
	public String editCategory(CategoryModel categoryModel) {
		Category categoryEntity = categoryRepository.findOneByCategoryId(categoryModel.getCategoryId());
		categoryEntity.setCategoryName(categoryModel.getCategoryName());
		categoryEntity.setDescription(categoryModel.getDescription());
		categoryEntity.setUrl(categoryModel.getUrl());
		categoryEntity.setParentId(categoryModel.getParentId());
		categoryRepository.save(categoryEntity);
		return "Sửa danh mục thành công";
	}
}
