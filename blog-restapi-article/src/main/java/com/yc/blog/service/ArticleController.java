package com.yc.blog.service;

import javax.annotation.Resource;

import com.yc.blog.bean.Article;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/blog")
public class ArticleController {
	
	@Resource
	private ArticleService articleService;
	
//	@Resource
//	private CategoryMapper cm;
	

	/*
	@GetMapping("toAddArticle")
	public String toaddArticle(Model m) {
		m.addAttribute("clist", cm.selectAll());
		return "addArticle";
	}

	@PostMapping("addArticle")
	public String addArticle(Article article,@SessionAttribute User loginedUser) {
		//业务验证自行添加
		
		article.setAuthor(loginedUser.getName());
		am.AddArticle(article);
		return "redirect:article.html?id=" + article.getId();
	}*/

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
}
