package com.linn.blog.entity.extension;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Article implements Serializable{

	private Integer id;
	
	private Integer categoryId;
	
	private String title;
	
	private String content;
	
	private Date lastTime;
	
	private String description;
	
	private String descriptionPic;
	
	private Integer isDraft;
	
	private String categoryName;//冗余字段，为了显示方便

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionPic() {
		return descriptionPic;
	}

	public void setDescriptionPic(String descriptionPic) {
		this.descriptionPic = descriptionPic;
	}

	public Integer getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	
	@Override
	public String toString() {
		return "Article [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", content=" + content + ", id=" + id
				+ ", intro=" + description + ", introPicName=" + descriptionPic
				+ ", isDraft=" + isDraft + ", lastTime=" + lastTime
				+ ", title=" + title + "]";
	}
}
