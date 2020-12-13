package com.yc.blog.dao;

import java.util.List;

import com.yc.blog.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {
	
	@Select("select * from category")
	List<Category> selectAll();
	
	@Select("select * from category where id=#{id}")
	Category selectById(int id);
}
