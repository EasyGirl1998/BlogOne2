package com.yc.blog.dao.impl;

import java.util.List;

import com.yc.blog.bean.Article;
import com.yc.blog.dao.MisBaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * 可以使用Tk.mybatis和 mybatis混合使用
 */

@Mapper
public interface ArticleMapper extends MisBaseMapper<Article> {
	
	@Select("select * from article order by id desc")
	@Results(id="rm",value={
	   @Result(column = "categoryid",property = "category",one=@One(select =
	  "com.yc.blog.dao.impl.CategoryMapper.selectById"))} )
	List<Article> selectNewArticle();
		
	@Select("select * from article order by readCnt desc")
	@ResultMap("rm")
	List<Article> selectByHot();
	
	@Select("select * from article where id=#{id}")
	@ResultMap("rm")
	Article selectById(int id);
	
	@Select("select a.* from article a join category b on a.categoryid = b.id where categoryid=#{id}")
	@ResultMap("rm")
	List<Article> selectByCategory(int id);
	
	@Insert("insert into article values(null,#{author},#{title},#{content},null,null"
			+ ",#{categoryid},#{label},null,null,now(),0,0)")
	//获取自增列的主键的值
	@Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
	int AddArticle(Article article);
}
