package com.yc.blog.domain;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Data
@Table(name = "article")
@ToString
public class Article implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String author;
	@NotEmpty
	private String title;
	@NotEmpty(message="文章的内容不能为空")
	@Length(min= 50,max = 2000,message = "内容长度在50-2000之间")
	private String content;
	private String keywords;
	private String description;
	@Min(1)
	private Integer categoryid;
	@NotEmpty
	private String label;
	private String titleimgs;
	private String status;
	private Timestamp createtime;
	private Integer readcnt;
	private Integer agreecnt;
	
	/**
	 * 	所属分类对象
	 */
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
