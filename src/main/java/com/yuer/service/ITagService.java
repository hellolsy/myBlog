package com.yuer.service;

import java.util.List;

import com.yuer.entity.Tag;

public interface ITagService {

	// 增删改查

	// 增加
	Integer saveTag(Tag tag);

	// 删除
	void deleteTag(Long id);

	// 改
	Integer updateTag(Long id, String tagName);

	// 查
	Tag getTagById(Long id);
	
	// 根据名字查
	Tag getTagByTagName(String tagName);

	// 全查出来
	List<Tag> listTag();

	// 用来做首页的根据分类下的博客数量最高的几个分类
	List<Tag> listTagTop(Integer size);

	// 根据start和size查询
	List<Tag> listTagByParam(int start, int size);
	
	Integer getTotal();

}
