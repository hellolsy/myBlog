package com.yuer.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuer.entity.Blog;
import com.yuer.entity.BlogQuery;

public interface IBlogService {

	// 增删改查

	// 增加
	Integer saveBlog(Blog blog);

	// 删除
	void deleteBlog(Long id);

	// 改
	Integer updateBlog(Blog blog);

	// 查
	Blog getBlogById(Long id);

	// 根据名字查
	Blog getBlogByBlogName(String blogName);

	// 全查出来
	List<Blog> listBlog();

	List<Blog> listBlogTop(Integer size);

	// 根据start和size查询
	List<Blog> listBlogByParam(int start, int size);

	// 前台的不需要显示草稿状态的
	List<Blog> listBlogByParamAndPublished(int start, int size);

	// 多条件组合查询（后台的搜索）
	List<Blog> getBlogByParams(@Param("blog") BlogQuery blog, @Param("start") Integer start,
			@Param("size") Integer size);

	Integer getTotal();

	Integer getTotalAndPublished();

	Integer getTotalAndPublishedAndSearch(String query);

	Integer getTotalAndPublishedAndTypeId(Long id);

	// 根据组合查询出的总数
	Integer getTotalByParams(BlogQuery blog);

	// 根据博客id找出对应得tagId集合
	List<Long> getTags(Long blogId);

	List<Blog> listBlogBySerach(@Param("query") String query, @Param("start") Integer start,
			@Param("size") Integer size);

	List<Blog> listBlogByTypeId(Long id, Integer start, Integer size);

	List<Blog> listBlogByTagId(Long id, int start, int size);

	Blog getAndConvert(Long id);

	Integer getTotalAndPublishedAndTagId(Long id);

	Map<String, List<Blog>> archiveBlog();

}
