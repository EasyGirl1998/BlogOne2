package com.yc.blog.client;

import com.yc.blog.config.FeignClientConfig;
import com.yc.blog.domain.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",configuration = FeignClientConfig.class)
public interface ArticleClient {

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/blog-proxy/blog/{id}")
    String findById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/blog-proxy/blog/findNew",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findNew();

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/blog-proxy/blog/findHot",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findHot();

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/blog-proxy/blog/findCategory/{id}")
    String findByCategory(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/blog-proxy/blog/category")
    String findCategory();

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/blog-proxy/blog/addArticle",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String addArticle(@RequestBody Article article);
}
