package com.vnpt.webappraw.model;

import java.util.List;

public class CategoryItem {
	private Long categoryId;
	private String categoryName;
	private String url;
	private List<CategoryItem> listSubCategory;
	private List<String> listFunctionAccept;
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getListFunctionAccept() {
		return listFunctionAccept;
	}
	public void setListFunctionAccept(List<String> listFunctionAccept) {
		this.listFunctionAccept = listFunctionAccept;
	}
	public List<CategoryItem> getListSubCategory() {
		return listSubCategory;
	}
	public void setListSubCategory(List<CategoryItem> listSubCategory) {
		this.listSubCategory = listSubCategory;
	}
	
}
