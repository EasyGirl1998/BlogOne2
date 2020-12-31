package com.yc.blog.service;

import com.yc.blog.bean.User;
import com.yc.blog.biz.BizException;
import com.yc.blog.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired(required = false)
    private AdminMapper am;


    public User login(User user) throws BizException {
        User aduser = am.SelectByAccountPwd(user);
        System.out.println(aduser);
        if(aduser == null) {
            throw new BizException("用户名或密码错误");
        }
        return aduser;
    }
}
