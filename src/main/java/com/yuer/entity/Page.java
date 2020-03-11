package com.yuer.entity;

import java.util.ArrayList;
import java.util.List;

// 进行分页的工具类
public class Page<T> {

	private List<T> content = new ArrayList<>();

	// 当前页数 默认为1
	private int page = 1;

	// 总页数
	private int totalPages = 0;

	// 这个等于(page - 1) * size，代表该显示多少条数据的第一个索引
	private int start = 0;
	
	// 默认为4
	private int size = 4;

	// 当前是不是首页，默认为true
	private boolean first = true;

	// 当前是不是尾页，默认是false
	private boolean last = false;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStart() {
		this.start = (page - 1) * size;
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isFirst() {
		if (page != 1) {
			this.first = false; 
		} else {
			this.first = true; 
		}
		
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		if (page != totalPages) {
			this.last = false; 
		} else {
			this.last = true; 
		}
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public Page() {
		start = (page - 1) * size;
	}

	

}
