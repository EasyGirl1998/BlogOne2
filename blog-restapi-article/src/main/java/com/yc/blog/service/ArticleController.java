package com.yc.blog.service;

import javax.annotation.Resource;

import com.yc.blog.domain.Article;
import com.yc.blog.domain.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/blog")
public class ArticleController {
	
	@Resource
	private ArticleService articleService;

	@GetMapping(value = "/{id}")
	public Article findById(@PathVariable Integer id){
		return articleService.findById(id);
	}

	@GetMapping(value = "/findNew")
	public List<Article> findNew(){
		return articleService.findNewArticle();
	}

	@GetMapping(value = "/findHot")
	public List<Article> findHot(){
		return articleService.findHotArticle();
	}

	@GetMapping(value = "/findCategory/{id}")
	public List<Article> findCategory(@PathVariable Integer id) {
		return articleService.findByCategory(id);
	}

	@GetMapping(value = "/category")
	public List<Category> Category() {
		return articleService.findCategory();
	}

	@PostMapping(value = "/addArticle")
	public int addArticle(@RequestBody Article article) {
		System.out.println(article);
		return articleService.addArticle(article);
	}
}
