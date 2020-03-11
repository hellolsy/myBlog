package com.yuer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuer.dao.ITypeDao;
import com.yuer.entity.Type;
import com.yuer.service.ITypeService;

@Service
public class TypeServiceImpl implements ITypeService {
	
	@Autowired
	private ITypeDao typeDao;

	@Override
	public Integer saveType(Type type) {
		return typeDao.saveType(type);
	}
	
	@Transactional
	@Override
	public void deleteType(Long id) {
		typeDao.deleteType(id);
		
	}
	
	
	@Transactional
	@Override
	public Integer updateType(Long id, String typeName) {
		// 先查看此id是否存在
		// 不存在就直接返回null
		Type t = typeDao.getTypeById(id);
		if (t == null) {
			return null;
		}
		
		return typeDao.updateType(id, typeName);
	}

	
	@Transactional
	@Override
	public Type getTypeById(Long id) {
		return typeDao.getTypeById(id);
	}

	@Transactional
	@Override
	public List<Type> listType() {
		return typeDao.listType();
	}

	@Transactional
	@Override
	public List<Type> listTypeByParam(int start, int size) {
		return typeDao.listTypeByParam(start, size);
	}

	@Transactional
	@Override
	public List<Type> listTypeTop(Integer size) {
		List<Type> types = typeDao.listTypeTop(size);
		
		// 再放置博客数量到type的blogNum中
		for (Type type : types) {
			type.setBlogNum(typeDao.listBlogNum(type.getId()));
		}
		
		return types;
	}

	@Transactional
	@Override
	public Integer getTotal() {
		return typeDao.getTotal();
	}

	@Transactional
	@Override
	public Type getTypeByTypeName(String typeName) {
		return typeDao.getTypeByTypeName(typeName);
	}

}
