package com.linn.blog.entity.extension;

import java.io.Serializable;

public class Category implements Serializable{

	private int id;
	private String code;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "ArticleCategory [code=" + code + ", id=" + id + ", name="
				+ name + "]";
	}
}
