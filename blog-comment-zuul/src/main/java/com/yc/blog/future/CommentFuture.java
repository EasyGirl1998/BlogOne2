package com.yc.blog.future;

import com.yc.blog.domain.Article;
import com.yc.blog.domain.Comment;
import com.yc.blog.service.CommentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class CommentFuture {

    @Autowired
    private CommentRestService commentRestService;

    @Async   //异步调用
    public CompletableFuture<String> selectByArticleId(Integer articleid) {
        return CompletableFuture.supplyAsync(() -> {
            return commentRestService.selectByArticleID(articleid);
        });
    }

    @Async   //异步调用
    public CompletableFuture<String> createComment(Comment comment) {
        return CompletableFuture.supplyAsync(() -> {
            return commentRestService.createComment(comment);
        });
    }
}
