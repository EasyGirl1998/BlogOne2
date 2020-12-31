package com.yc.blog.RestController;

import com.google.gson.Gson;
import com.yc.blog.biz.BizException;
import com.yc.blog.biz.UserBiz;
import com.yc.blog.dao.UserMapper;
import com.yc.blog.domain.Result;
import com.yc.blog.domain.User;
import com.yc.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
public class UserController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private UserBiz userBiz;

    public com.yc.blog.domain.User findByAccount(String account){
        return userMapper.selectByAccount(account);
    }

    @RequestMapping("/login")
    public com.yc.blog.domain.Result login(@Valid com.yc.blog.domain.User user, Errors errors, HttpSession session) {
        System.out.println("===="+user+"=====");
        //用户输入验证
        if(errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd")) {
            return new com.yc.blog.domain.Result(0,"字段值错误",errors.getAllErrors());
        }
        //业务层逻辑验证---》UserBiz
        try {
            //加密
            user.setPwd(MD5Utils.stringToMD5(user.getPwd()));
            com.yc.blog.domain.User dbuser = userBiz.login(user);
            session.setAttribute("loginedUser", dbuser);
            return new com.yc.blog.domain.Result(1,"登陆成功");
        } catch (BizException e) {
            e.printStackTrace();
            errors.rejectValue("account", "AccountORPasswordERROR", e.getMessage());
            return new com.yc.blog.domain.Result(0,"逻辑验证失败",errors.getAllErrors());
        }

    }

    @RequestMapping("/register")
    public int register(@Valid com.yc.blog.domain.User user, Errors errors, @RequestParam("account")String account, @RequestParam("pwd")String pwd, @RequestParam("name") String name, @RequestParam("email")String email, HttpServletResponse response)throws IOException {
        //设置默认（成功）跳转的页面
        //页面跳转行为：1、请求转发（默认） 2、响应重定向

        System.out.println("进入注册页面"+"55555555555555555555555555555"+user);
        System.out.println(errors.getAllErrors()+"-------");
        //验证用户输入的信息
        if (errors.hasErrors()) {
            try {
                System.out.println("6666666666666666");
                response.sendRedirect("http://localhost:8095/register.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        } else {
            try {
                //md5加密
                user.setPwd(MD5Utils.stringToMD5(user.getPwd()));
                System.out.println("77777777777");
                userBiz.register(user);
                System.out.println("8888888888888888");

            } catch (BizException e) {
                e.printStackTrace();
                errors.rejectValue("account", "AccountFailure", e.getMessage());
                try {
                    response.sendRedirect("http://localhost:8095/register.html");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return 0;
            }
        }
        try {
            response.sendRedirect("http://localhost:8095/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @GetMapping("forget.html")
    public ModelAndView toforget(ModelAndView mav) {
        mav.setViewName("forget");
        return mav;
    }

    //获取问题
    @GetMapping("getQuestion")
    public CompletableFuture<String> getQuestion(@RequestParam("account")String account) {
        return CompletableFuture.supplyAsync(() -> {
            if (account == null || account.trim().isEmpty()) {
                //return new Result(0, "用户名不能为空！");
                return new Gson().toJson(new com.yc.blog.domain.Result(0, "用户名不能为空！"));
            }
            com.yc.blog.domain.User user = userMapper.selectByAccount(account);
            if (user == null) {
                //return new Result(0, "该用户不存在！");
                return new Gson().toJson(new com.yc.blog.domain.Result(0, "该用户不存在！"));
            }
            //return new Result(1, user.getPwdQuestion());
            return new Gson().toJson(new com.yc.blog.domain.Result(1, user.getPwdQuestion()));
        });
    }


    //判断答案
    @GetMapping("answer")
    public CompletableFuture<String> answer(@RequestParam("account")String account, @RequestParam("pwdAnswer")String pwdAnswer) {
        return CompletableFuture.supplyAsync(() -> {
            if (account == null || account.trim().isEmpty()) {
                //return new Result(0, "用户名不能为空!");
                return new Gson().toJson(new com.yc.blog.domain.Result(0, "用户名不能为空!"));
            }
            if (pwdAnswer == null || pwdAnswer.trim().isEmpty()) {
                //return new Result(0, "密码回答不能为空!");
                return new Gson().toJson(new com.yc.blog.domain.Result(0, "密码回答不能为空!"));
            }
            User user = userMapper.selectByAccountAndPwdAnswer(account, pwdAnswer);
            if (user == null) {
                //return new Result(0, "问题回答不正确!");
                return new Gson().toJson(new com.yc.blog.domain.Result(0, "问题回答不正确!"));
            }
            //return new Result(1, "问题回答正确!");
            return new Gson().toJson(new com.yc.blog.domain.Result(1, "问题回答正确!"));
        });
    }

    //重置密码
    @GetMapping("resetPwd")
    public CompletableFuture<String> resetPwd(@RequestParam("account")String account,@RequestParam("pwd") String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            if (account == null || account.trim().isEmpty()) {
                //return new Result(0,"用户名不能为空!");
                return new Gson().toJson(new com.yc.blog.domain.Result(0, "用户名不能为空!"));
            }
            if (pwd == null || pwd.trim().isEmpty()) {
                //return new Result(0,"密码不能为空!");
                return new Gson().toJson(new com.yc.blog.domain.Result(0, "密码不能为空!"));
            }
            userMapper.resetPwd(account, MD5Utils.stringToMD5(pwd));
            //return new Result(1,"密码重置成功!");
            return new Gson().toJson(new com.yc.blog.domain.Result(1, "密码重置成功!"));
        });
    }

    //上传图片
    @PostMapping("uploadImg")
    public com.yc.blog.domain.Result uploadImg(@RequestParam("img") MultipartFile file) throws IllegalStateException, IOException {
        file.transferTo(new File("e:/cr_img/" + file.getOriginalFilename()));
        //回传 图片的web地址
        return new Result(1,"/imgs/"+file.getOriginalFilename());
    }

}
