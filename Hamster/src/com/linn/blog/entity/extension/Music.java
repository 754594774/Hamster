package com.linn.blog.entity.extension;

public class Music {

	private Integer id;
	
	private String title;
	
	private String linkAddress;
	
	private Integer isDeleted;

	private Integer displayOrder;
	
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	@Override
	public String toString() {
		return "Music [displayOrder=" + displayOrder + ", id=" + id
				+ ", isDeleted=" + isDeleted + ", linkAddress=" + linkAddress
				+ ", title=" + title + "]";
	}

}
