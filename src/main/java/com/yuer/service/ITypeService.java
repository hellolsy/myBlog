package com.yuer.service;

import java.util.List;

import com.yuer.entity.Type;

public interface ITypeService {

	// 增删改查

	// 增加
	Integer saveType(Type type);

	// 删除
	void deleteType(Long id);

	// 改
	Integer updateType(Long id, String typeName);

	// 查
	Type getTypeById(Long id);
	
	// 根据名字查
	Type getTypeByTypeName(String typeName);

	// 全查出来
	List<Type> listType();

	// 用来做首页的根据分类下的博客数量最高的几个分类
	List<Type> listTypeTop(Integer size);

	// 根据start和size查询
	List<Type> listTypeByParam(int start, int size);
	
	Integer getTotal();

}
