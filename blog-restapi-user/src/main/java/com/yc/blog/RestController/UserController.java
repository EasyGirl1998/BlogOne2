package com.yc.blog.RestController;

import com.yc.blog.bean.Result;
import com.yc.blog.bean.User;
import com.yc.blog.biz.BizException;
import com.yc.blog.service.UserService;
import com.yc.blog.util.MD5Utils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/find/{account}")
    public User findByAccount(@PathVariable String account) {
        return userService.findByAccount(account);
    }

    @GetMapping("/login")
    public Result login(@Valid User user, Errors errors, HttpSession session) {
        return userService.login(user,errors,session);
    }

    @RequestMapping("/register")
    public ModelAndView register(@Valid User user, Errors errors, ModelAndView mav) {
        return userService.register(user,errors,mav);
    }


}
