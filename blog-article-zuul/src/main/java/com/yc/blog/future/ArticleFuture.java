package com.yc.blog.future;

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
}
