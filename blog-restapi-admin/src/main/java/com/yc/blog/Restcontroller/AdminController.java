package com.yc.blog.Restcontroller;


import com.yc.blog.bean.User;
import com.yc.blog.biz.BizException;
import com.yc.blog.service.AdminService;

import com.yc.blog.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

   @Autowired
   private AdminService adminService;


    @RequestMapping("/login")
    public User login(@RequestParam("account")String account, @RequestParam("pwd")String pwd) throws BizException {
        User user = new User();
        user.setAccount(account);
        user.setPwd(MD5Utils.stringToMD5(pwd));
        return adminService.login(user);
    }


}
