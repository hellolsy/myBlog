package com.yuer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuer.dao.ITagDao;
import com.yuer.entity.Tag;
import com.yuer.service.ITagService;

@Service
public class TagServiceImpl implements ITagService {

	@Autowired
	private ITagDao tagDao;

	@Override
	public Integer saveTag(Tag tag) {
		return tagDao.saveTag(tag);
	}

	@Transactional
	@Override
	public void deleteTag(Long id) {
		tagDao.deleteTag(id);

	}

	@Transactional
	@Override
	public Integer updateTag(Long id, String tagName) {
		// 先查看此id是否存在
		// 不存在就直接返回null
		Tag t = tagDao.getTagById(id);
		if (t == null) {
			return null;
		}

		return tagDao.updateTag(id, tagName);
	}

	@Transactional
	@Override
	public Tag getTagById(Long id) {
		return tagDao.getTagById(id);
	}

	@Transactional
	@Override
	public List<Tag> listTag() {
		return tagDao.listTag();
	}

	@Transactional
	@Override
	public List<Tag> listTagByParam(int start, int size) {
		return tagDao.listTagByParam(start, size);
	}

	@Transactional
	@Override
	public List<Tag> listTagTop(Integer size) {
		List<Tag> tags = tagDao.listTagTop(size);

		// 再放置博客数量到tag的blogNum中
		for (Tag tag : tags) {
			tag.setBlogNum(tagDao.listBlogNum(tag.getId()));
		}

		return tags;
	}

	@Transactional
	@Override
	public Integer getTotal() {
		return tagDao.getTotal();
	}

	@Transactional
	@Override
	public Tag getTagByTagName(String tagName) {
		return tagDao.getTagByTagName(tagName);
	}

}
