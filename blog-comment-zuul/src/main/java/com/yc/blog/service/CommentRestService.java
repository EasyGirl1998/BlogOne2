package com.yc.blog.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.blog.client.CommentClient;
import com.yc.blog.domain.Article;
import com.yc.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class CommentRestService {

    @Autowired
    private CommentClient commentClient;

    @HystrixCommand(fallbackMethod = "selectByArticleIDFallback")
    public String selectByArticleID(Integer articleid) {

        return commentClient.selectByArticleId(articleid);
    }

    private String selectByArticleIDFallback(Integer articleid) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createCommentFallback")
    public String createComment(Comment comment) {

        return commentClient.createComment(comment);
    }

    private String createCommentFallback(Comment comment) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
}
