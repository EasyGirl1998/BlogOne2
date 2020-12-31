package com.yc.blog.service;

import com.yc.blog.dao.CommentMapper;
import com.yc.blog.domain.Comment;
import com.yc.blog.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.util.List;

@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;


    public List<Comment> selectByArticleid(int articleid) {
        return commentMapper.selectByArticleId(articleid);
    }

    public int create(@Valid Comment comment) {
       return commentMapper.insert(comment);
    }
}
