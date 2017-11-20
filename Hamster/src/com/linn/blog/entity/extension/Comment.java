package com.linn.blog.entity.extension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Comment {

	private Integer id;
	private Integer pid;
	private Integer rootid;
	private String memberName;
	private String cont;
	private Date pdate;
	private Integer isleaf;
	private Integer article_id ;
	private List<Comment> childComments = new ArrayList<Comment>();
	public List<Comment> getChildComments() {
		return childComments;
	}
	public void setChildComments(List<Comment> childComments) {
		this.childComments = childComments;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getRootid() {
		return rootid;
	}
	public void setRootid(Integer rootid) {
		this.rootid = rootid;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Integer getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer articleId) {
		article_id = articleId;
	}
	@Override
	public String toString() {
		return "Comment [article_id=" + article_id + ", childComments="
				+ childComments + ", cont=" + cont + ", id=" + id + ", isleaf="
				+ isleaf + ", memberName=" + memberName + ", pdate=" + pdate
				+ ", pid=" + pid + ", rootid=" + rootid + "]";
	}
	
}
