package com.yc.blog.dao;

import com.yc.blog.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends MisBaseMapper<User>{
    /**
     * 根据用户名和密码登录
     * @param user
     * @return
     */
    @Select("select * from user where account=#{account} and pwd=#{pwd}")
    User SelectByAccountPwd(User user);

}
