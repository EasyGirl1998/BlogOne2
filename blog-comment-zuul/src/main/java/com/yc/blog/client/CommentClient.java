package com.yc.blog.client;

import com.yc.blog.config.FeignClientConfig;
import com.yc.blog.domain.Article;
import com.yc.blog.domain.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",contextId = "comment",configuration = FeignClientConfig.class)
public interface CommentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/comment-proxy/comment/{articleid}")
    String selectByArticleId(@RequestParam("articleid")int articleid);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/comment-proxy/comment/reply",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String createComment(@RequestBody Comment comment);
}
