package com.yc.blog.future;

import com.yc.blog.service.AdminRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AdminFuture {

    @Autowired
    private AdminRestService adminRestService;

    @Async   //异步调用
    public CompletableFuture<String> selectByUser(String account,String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            return adminRestService.selectByUser(account,pwd);
        });
    }
}
