package com.yc.blog.web.controller;

import com.yc.blog.domain.Comment;
import com.yc.blog.domain.User;
import com.yc.blog.future.CommentFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentFuture  commentFuture;

    @RequestMapping(value = "/{articleid}",method = RequestMethod.GET)
    public CompletableFuture<String> selectByArticleId(@PathVariable Integer articleid) {
        return commentFuture.selectByArticleId(articleid);
    }

    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    public CompletableFuture<String> createComment(HttpSession session, @RequestBody Comment comment) {
        System.out.println("web层："+comment);
        User user=(User)session.getAttribute("loginedUser");
        String account = user.getAccount();
        System.out.println("这是web端的："+comment);
        // return null;
        return commentFuture.createComment(comment);
    }
}
