package com.yuer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuer.entity.Blog;
import com.yuer.entity.BlogQuery;

public interface IBlogDao {

	// 增加
	Integer saveBlog(Blog blog);

	Integer saveTags(Long blogId, Long tagId);

	Integer deleteTags(Long blogId);

	List<Long> getTags(Long blogId);

	// 删除
	void deleteBlog(Long id);

	// 改
	Integer updateBlog(Blog blog);

	// 查
	Blog getBlogById(Long id);

	// 全查出来
	List<Blog> listBlog();

	List<Blog> listBlogTop(Integer size);

	// 根据start和size查询
	List<Blog> listBlogByParam(Integer start, Integer size);

	// 前台的
	List<Blog> listBlogByParamAndPublished(Integer start, Integer size);

	// 获取总数据量
	Integer getTotal();

	// 获取发布的总数据量
	Integer getTotalAndPublished();

	//
	Integer getTotalAndPublishedAndSearch(String query);

	Integer getTotalAndPublishedAndTypeId(Long id);

	Integer getTotalAndPublishedAndTagId(Long id);

	// 根据BlogName获取
	Blog getBlogByBlogName(String blogName);

	// 组合查询后再分页
	public List<Blog> getBlogByParams(@Param("blog") BlogQuery blog, @Param("start") Integer start,
			@Param("size") Integer size);

	// 根据组合查询出的总数
	Integer getTotalByParams(BlogQuery blog);

	// 根据搜索结果再分页
	List<Blog> listBlogBySerach(@Param("query") String query, @Param("start") Integer start,
			@Param("size") Integer size);

	List<Blog> listBlogByTypeId(Long id, Integer start, Integer size);

	List<Blog> listBlogByTagId(Long id, Integer start, Integer size);

	Integer updateViewCounts(Long id);

	List<String> findGroupYear();

	List<Blog> findByYear(String year);
}
