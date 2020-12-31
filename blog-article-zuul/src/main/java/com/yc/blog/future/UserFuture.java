package com.yc.blog.future;

import com.yc.blog.domain.User;
import com.yc.blog.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Component
public class UserFuture {
    @Autowired
    private UserRestService userRestService;


    /*@Async   //异步调用
    public CompletableFuture<User> findByAccount(String account) {
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.findByAccount(account);
        });
    }*/

    @Async
    public CompletableFuture<String> login(User user, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("zuulClient"+user+"errors:"+errors);
            return userRestService.login(user,errors,session,account,pwd);
        });
    }

    @Async
    public CompletableFuture<String> register(@Valid User user,Errors errors, @RequestParam("account")String account, @RequestParam("pwd")String pwd, @RequestParam("name")String name, @RequestParam("email")String email) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("2222222222222222222222222");
            return userRestService.register(user,errors,account,pwd,name,email);
        });
    }

    @Async
    public CompletableFuture<String> getQuestion(@RequestParam("account")String account){
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.getQuestion(account);
        });
    }

    @Async
    public CompletableFuture<String> answer(@RequestParam("account")String account, @RequestParam("pwdAnswer")String pwdAnswer){
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.answer(account, pwdAnswer);
        });
    }

    @Async
    public CompletableFuture<String> resetPwd(@RequestParam("account")String account,@RequestParam("pwd") String pwd){
        return CompletableFuture.supplyAsync(() -> {
            return userRestService.resetPwd(account, pwd);
        });
    }

}
