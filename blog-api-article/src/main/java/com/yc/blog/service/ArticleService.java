package com.yc.blog.service;


import com.yc.blog.dao.ArticleMapper;
import com.yc.blog.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
	
	@Autowired(required = false)
	private ArticleMapper am;

	//按id查询
	public Article findById(Integer id){
		return am.selectById(id);
	}

	//查询最新的文章
	public List<Article> findNewArticle(){
		return am.selectNewArticle();
	}

	//查询访问量靠前的文章
	public List<Article> findHotArticle(){

		return am.selectByHot();
	}

	//分类查询文章
	public List<Article> findByCategory(Integer id) {
		return am.selectByCategory(id);
	}

	//新增文章
	public int addArticle(Article article){

		return am.AddArticle(article);
	}
}
