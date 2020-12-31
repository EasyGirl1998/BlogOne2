package com.yc.blog.service;

import com.yc.blog.domain.Comment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @RequestMapping(value= "/{articleid}",method = RequestMethod.GET)
    public List<Comment> selectByArticleId(@PathVariable int articleid){
        return commentService.selectByArticleid(articleid);
    }

    @RequestMapping(value= "/reply",method = RequestMethod.POST)
    public int create(@RequestBody @Valid Comment comment) {
        return commentService.create(comment);
    }
}
