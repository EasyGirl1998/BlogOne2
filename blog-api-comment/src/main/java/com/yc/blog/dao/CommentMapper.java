package com.yc.blog.dao;


import com.yc.blog.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends MisBaseMapper<Comment>{

	/*
	用户模块整合过来再启用
	@Insert("insert into comment values(null,#{articleid},#{content},#{createby},now())")
	int insert(Comment comment);
	*/

	@Insert("insert into comment values(null,#{articleid},#{content},null,now())")
	int insert(Comment comment);
	
	@Select("select * from comment where articleid=#{articleid}")
	List<Comment> selectByArticleId(int articleid);
}
