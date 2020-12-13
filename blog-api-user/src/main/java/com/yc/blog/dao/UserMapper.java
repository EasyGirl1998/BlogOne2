package com.yc.blog.dao;

import com.yc.blog.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	
	@Select("select * from user where account=#{account} and pwd=#{pwd}")
	User selectByAccountPwd(User user);
	
	@Select("select count(*) from user where account=#{account}")
	int selectByAccountCount(User user);
	
	@Insert("insert into user values(null,#{name},#{account},#{pwd},#{phone},#{email},#{head},now(),#{status},#{type},#{pwdQuestion},#{pwdAnswer})")
	void addUser(User user);
	
	@Select("select * from user where account = #{account}")
	User selectByAccount(String account);
	
	@Select("select * from user where account=#{account} and pwd_answer=#{pwdAnswer}")
	User selectByAccountAndPwdAnswer(@Param(value = "account") String account,@Param(value="pwdAnswer") String pwdAnswer);
	
	@Update("update user set pwd = #{pwd} where account = #{account}")
	void resetPwd(@Param("account") String account,@Param("pwd") String pwd);
}
