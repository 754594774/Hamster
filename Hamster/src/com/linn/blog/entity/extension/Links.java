package com.linn.blog.entity.extension;

public class Links {
	private Integer id;
	private String name;
	private String url;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Links [id=" + id + ", name=" + name + ", remark=" + remark
				+ ", url=" + url + "]";
	}

	
}
