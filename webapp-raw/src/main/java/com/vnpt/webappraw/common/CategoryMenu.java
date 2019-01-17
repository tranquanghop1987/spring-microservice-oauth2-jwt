package com.vnpt.webappraw.common;

import java.util.ArrayList;
import java.util.List;

import com.vnpt.webappraw.model.CategoryItem;
import com.vnpt.webappraw.model.CategoryModel;

public class CategoryMenu {
	public List<CategoryItem> getCategoryMenu(List<CategoryModel> listCategory){
		List<CategoryItem> listCategoryItem = getAllCategoryItem(listCategory, null);
		return listCategoryItem;
	}
	
	private List<CategoryItem> getAllCategoryItem(List<CategoryModel> listCategory, Long parentId){
		List<CategoryItem> returnValue = new ArrayList<>();
		
		if(parentId == null) {
			for(CategoryModel item : listCategory) {
				if(item.getParentId() == null) {
					CategoryItem categoryItem = new CategoryItem();
					categoryItem.setCategoryId(item.getCategoryId());
					categoryItem.setCategoryName(item.getCategoryName());
					categoryItem.setUrl(item.getUrl());
					List<CategoryItem> listSubCategoryItem = getAllCategoryItem(listCategory, item.getCategoryId());
					categoryItem.setListSubCategory(listSubCategoryItem);
					returnValue.add(categoryItem);
				}
			}
		}else {
			for(CategoryModel item : listCategory) {
				if(item.getParentId() == parentId) {
					CategoryItem categoryItem = new CategoryItem();
					categoryItem.setCategoryId(item.getCategoryId());
					categoryItem.setCategoryName(item.getCategoryName());
					categoryItem.setUrl(item.getUrl());
					List<CategoryItem> listSubCategoryItem = getAllCategoryItem(listCategory, item.getCategoryId());
					categoryItem.setListSubCategory(listSubCategoryItem);
					returnValue.add(categoryItem);
				}
			}
		}
		
		return returnValue;
	}
}
