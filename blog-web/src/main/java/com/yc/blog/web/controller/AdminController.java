package com.yc.blog.web.controller;

import com.yc.blog.future.AdminFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminFuture adminFuture;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public CompletableFuture<String> selectByUser(@RequestParam("account")String account,@RequestParam("pwd")String pwd) {
       return adminFuture.selectByUser(account,pwd);
    }
}
