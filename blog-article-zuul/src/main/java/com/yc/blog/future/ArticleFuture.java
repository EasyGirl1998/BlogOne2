package com.yc.blog.future;

import com.yc.blog.domain.Article;
import com.yc.blog.service.ArticleRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class ArticleFuture {

    @Autowired
    private ArticleRestService articleRestService;

    @Async   //异步调用
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.findById(id);
        });
    }

    @Async   //异步调用
    public CompletableFuture<String> findNew() {
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.findNew();
        });
    }

    @Async   //异步调用
    public CompletableFuture<String> findHot() {
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.findHot();
        });
    }

    @Async   //异步调用
    public CompletableFuture<String> findByCategory(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.findByCategory(id);
        });
    }

    @Async   //异步调用
    public CompletableFuture<String> findCategory() {
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.findCategory();
        });
    }

    @Async   //异步调用
    public CompletableFuture<String> addArticle(Article article) {
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.addArticle(article);
        });
    }
}
