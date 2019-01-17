package com.vnpt.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vnpt.category.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	@Query(value="SELECT c FROM Category c WHERE c.categoryName like %:keyword%")
	public List<Category> findAllByCategoryName(@Param("keyword")String keyword);
	
	public Category findOneByCategoryId(Long categoryId);
}
