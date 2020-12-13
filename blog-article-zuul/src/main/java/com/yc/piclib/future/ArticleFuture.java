package com.yc.piclib.future;

import com.yc.blog.bean.Article;
import com.yc.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class ArticleFuture {

    @Autowired
    private ArticleService articleService;

    @Async   //异步调用
    public CompletableFuture<Article> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return articleService.findById(id);
        });
    }
}
