package com.linn.blog.entity.extension;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Comment {

	private Integer id;
	private Integer pid;
	private Integer rootid;
	private String memberName;
	private String cont;
	private Timestamp pdate;
	private Integer isleaf;
	private Integer articleId ;
	private String articleTitle;
	private Integer isDeleted;
	
	
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
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
	public Timestamp getPdate() {
		return pdate;
	}
	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}
	public Integer getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "Comment [articleId=" + articleId + ", articleTitle="
				+ articleTitle + ", childComments=" + childComments + ", cont="
				+ cont + ", id=" + id + ", isDeleted=" + isDeleted
				+ ", isleaf=" + isleaf + ", memberName=" + memberName
				+ ", pdate=" + pdate + ", pid=" + pid + ", rootid=" + rootid
				+ "]";
	}


	
}
