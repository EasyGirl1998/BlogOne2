package com.yc.blog.service;

import javax.annotation.Resource;

import com.yc.blog.bean.Article;
import com.yc.blog.dao.impl.ArticleMapper;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleService {
	
	@Resource
	private ArticleMapper am;

	public Article findById(Integer id){
		/*
		尚未解决的问题是  使用ArticleMapper.selectById();方法的时候出现的问题(查询出错)
		这里暂时使用的时tk.mybatis提供的selectByPrimaryKey()方法替代
		* */
		return am.selectByPrimaryKey(id);
	}
}
