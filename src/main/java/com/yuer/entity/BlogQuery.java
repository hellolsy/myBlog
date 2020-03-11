package com.yuer.entity;

/*
 * 作为一个查询的条件对象
 */
public class BlogQuery {

	private String title;
	
	private Long typeId;
	
	private Boolean isRecommend;
	
	
	
	
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	
	
	
}
