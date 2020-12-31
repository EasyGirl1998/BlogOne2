package com.yc.blog.dao;


import com.yc.blog.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends MisBaseMapper<User> {

	@Select("select * from user where account=#{account} and pwd=#{pwd}")
	User selectByAccountPwd(User user);

	@Select("select count(*) from user where account=#{account}")
	int selectByAccountCount(User user);

	@Insert("insert into user(name,account,pwd,email) values(#{name},#{account},#{pwd},#{email})")
	int addUser(User user);

	//@Select("select * from user where account = #{account}")
	@Select("select * from user where account = #{account}")
	@Results(id = "umrm",value = { @Result(column = "pwd_question",property = "pwdQuestion"),
			@Result(column = "pwd_answer",property = "pwdAnswer")})
	User selectByAccount(String account);

	@Select("select * from user where account = #{account} and pwd_answer = #{pwdAnswer}")
	@ResultMap("umrm")
	User selectByAccountAndPwdAnswer(@Param(value = "account") String account,@Param(value="pwdAnswer") String pwdAnswer);

	@Update("update user set pwd = #{pwd} where account = #{account}")
	void resetPwd(@Param("account") String account,@Param("pwd") String pwd);
}
