/*
package com.yc.blog.service;

import com.yc.blog.bean.Result;
import com.yc.blog.bean.User;
import com.yc.blog.biz.BizException;
import com.yc.blog.biz.UserBiz;
import com.yc.blog.dao.UserMapper;
import com.yc.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private UserBiz userBiz;

    public User findByAccount(String account){
        return userMapper.selectByAccount(account);
    }

    public Result login(@Valid User user, Errors errors, HttpSession session) {
        //用户输入验证
        if(errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd")) {
            return new Result(0,"字段值错误",errors.getAllErrors());
        }
        //业务层逻辑验证---》UserBiz
        try {
            //加密
            user.setPwd(MD5Utils.stringToMD5(user.getPwd()));
            User dbuser = userBiz.login(user);
            session.setAttribute("loginedUser", dbuser);
            return new Result(1,"登陆成功");
        } catch (BizException e) {
            e.printStackTrace();
            errors.rejectValue("account", "AccountORPasswordERROR", e.getMessage());
            return new Result(0,"逻辑验证失败",errors.getAllErrors());
        }

    }

    public ModelAndView register(@Valid User user, Errors errors, ModelAndView mav) {
        //设置跳转页面，响应重定项
        mav.setViewName("redirect:index");

        if(errors.hasErrors()) {
            mav.setViewName("register");
        } else {

            try {
                //加密
                user.setPwd(MD5Utils.stringToMD5(user.getPwd()));
                userBiz.register(user);
            } catch (BizException e) {

                e.printStackTrace();
                errors.rejectValue("account", "ACCOUNTERROR", e.getMessage());
                mav.setViewName("register");

            }

        }
        //将用户对象传回给页面，实现表单回填
        mav.addObject("errors", errors.getAllErrors());
        mav.addObject("user",user);
        return mav;
    }

    public ModelAndView toreg(ModelAndView mav) {
        mav.setViewName("register");
        return mav;
    }

    public ModelAndView toForget(ModelAndView mav) {
        mav.setViewName("forget");
        return mav;
    }

    public Result togetQuestion(String account) {
        if(account == null || account.trim().isEmpty()) {
            return new Result(0,"用户名为空");
        }

        User user = userMapper.selectByAccount(account);

        if(user == null) {
            return new Result(0,"用户名不存在！");
        }

        return new Result(1,user.getPwdQuestion());
    }

    public Result togetAnswer(String account,String pwdAnswer) {
        if(account == null || account.trim().isEmpty()) {
            return new Result(0,"用户名为空");
        }

        if(pwdAnswer == null || pwdAnswer.trim().isEmpty()) {
            return new Result(0,"请先回答问题");
        }

        User user = userMapper.selectByAccountAndPwdAnswer(account, pwdAnswer);

        if(user == null) {
            return new Result(0,"密保答案验证错误！");
        }

        return new Result(1,"密保验证正确");
    }

    public Result resetpwd(String account,String pwd) {

        if(account == null || account.trim().isEmpty()) {
            return new Result(0,"用户名为空");
        }

        if(pwd == null || pwd.trim().isEmpty()) {
            return new Result(0,"密码");
        }

        userMapper.resetPwd(account, MD5Utils.stringToMD5(pwd));
        return new Result(1,"密码修改成功");
    }

   */
/* public Result uploadImg(@RequestParam("img") MultipartFile file) throws IllegalStateException, IOException {
        file.transferTo(new File("D:/JavaStudyFiles/Season-3/photo/uploadpics/"+file.getOriginalFilename()));
        //回传图片的web路径
        return new Result(1,"/imgs/"+file.getOriginalFilename());
        //return new Result(1,"文件上传成功！");
    }*//*


}
*/
