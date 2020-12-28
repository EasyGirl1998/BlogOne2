package com.yc.blog.dao;

import java.util.List;

import com.yc.blog.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper extends MisBaseMapper<Category> {
	
	@Select("select * from category")
	List<Category> selectAll();
	
	@Select("select * from category where id=#{id}")
	Category selectById(int id);
}
