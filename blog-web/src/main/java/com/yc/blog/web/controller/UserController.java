package com.yc.blog.web.controller;

import com.google.gson.Gson;
import com.yc.blog.dao.UserMapper;
import com.yc.blog.domain.User;
import com.yc.blog.future.UserFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
//@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired//(required = false)
    private UserFuture userFuture;

    @Autowired(required = false)
    private UserMapper um;
       /* @GetMapping(value = "/find/{account}")
        public CompletableFuture<User> findByAccount(@PathVariable String account) {

            return userFuture.findByAccount(account);
        }*/

    @RequestMapping("/login")
    public CompletableFuture<String> login(@Valid User user, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        // System.out.println("web层"+user+"errors:"+errors);;
        session.setAttribute("loginedUser", user);
        System.out.println(session.getAttribute("loginedUser")+"这是session");
        return userFuture.login(user,errors,session,account,pwd);
    }

    @RequestMapping("/register")                                //,
    public CompletableFuture<String> register(@Valid User user ,Errors errors, @RequestParam("account")String account, @RequestParam("pwd")String pwd, @RequestParam("name")String name, @RequestParam("email")String email){
        System.out.println("web层"+user+"|"+account+"|"+pwd+"|"+name+"|"+email+"11111111111111111111111111111111111");
        return userFuture.register(user,errors,account,pwd,name,email);
    }

    @RequestMapping("/doCheck")
    public String doCheck(HttpSession session) throws IOException {
        User user=(User)session.getAttribute("loginedUser");
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("user", user);
        return new Gson().toJson(map);
    }

}
