package com.yc.blog.web.controller;


import com.yc.blog.domain.Article;
import com.yc.blog.future.ArticleFuture;
import com.yc.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/blog")
public class ArticleController {

    @Autowired
    private ArticleFuture articleFuture;

    @Autowired
    private ArticleService articleService;

    private static Logger logger = LoggerFactory.getLogger(ArticleController.class.getName());


    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        return articleFuture.findById(id);
    }

    @GetMapping(path= {"index","index.html","/"})
    //  , @RequestParam(defaultValue = "1") int page
    public String Index(Model m) {

    //    PageHelper.startPage(page, 5);
    //    List<Article> newArticles = am.selectNewArticle();
    //    PageHelper.startPage(page, 5);
    //    List<Article> hotArticles = am.selectByHot();
    //    List<Category> categorys =  cm.selectAll();

    //    m.addAttribute("newArticles", newArticles);
    //    m.addAttribute("hotArticle",hotArticles);
    //    m.addAttribute("categorys",categorys);
        CompletableFuture<String> article = articleFuture.findById(15);
        m.addAttribute("article",article);
      /* Article article =  articleService.findById(15);
       m.addAttribute("article",article);*/
        return "index";
    }


   /* @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public CompletableFuture<String> findAll(Integer page, Integer pageSize, String description) {
        return piclibFuture.findPage(page, pageSize, description);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody PicDomain picDomain) throws Exception {
        return piclibFuture.create(picDomain);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return piclibFuture.delete(id);
    }*/
}
