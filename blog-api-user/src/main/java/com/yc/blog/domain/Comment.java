package com.yc.blog.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

public class Comment implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer articleid;
	@NotEmpty(message="评论的内容不能为空")
	@Length(min=1,max=1000,message="超过输入的字符上限！")
	private String content;
	private Integer createby;
	private Timestamp createtime;
	
	private Article article; // 关联文章   一对一管理
	private User user; 		 // 发表人, 关联 User  一对一管理
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCreateby() {
		return createby;
	}
	public void setCreateby(Integer createby) {
		this.createby = createby;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
}
