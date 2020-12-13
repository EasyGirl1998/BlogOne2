package com.yc.blog.biz;

import javax.annotation.Resource;

import com.yc.blog.bean.User;
import com.yc.blog.dao.UserMapper;
import org.springframework.stereotype.Service;


@Service
public class UserBiz {
	
	@Resource
	private UserMapper um;
	
	//业务层逻辑验证
	public User login(User user) throws BizException {
		
		User dbuser = um.selectByAccountPwd(user);
		
		if(dbuser == null) {
			throw new BizException("账户名或密码错误！");
		}
		return dbuser;
	}
	
	public void register(User user) throws BizException {
		
		if(um.selectByAccountCount(user) > 0) {
			throw new BizException("该用户已存在");
		}
		
		um.addUser(user);
	}
}
