package com.yuer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuer.entity.Blog;
import com.yuer.entity.Page;
import com.yuer.entity.Type;
import com.yuer.service.IBlogService;
import com.yuer.service.ITypeService;

@Controller
public class TypeShowController {
	
	@Autowired
	private ITypeService typeService;
	
	@Autowired
	private IBlogService blogService;
	
	
	/**
	 * @RequestParam("page")这种会接受？page=1的值，即翻页中/(page=page.page+1)的page的值
	 * @param page
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/types/{id}")
	public String types(@RequestParam(value = "page", required = false) Integer page, @PathVariable Long id, Model model) {
		// 查全部的根据所包含博客数目排序
		List<Type> types = typeService.listTypeTop(10000);
		if (id == -1) {
			id = types.get(0).getId();
		}
		
		if (page == null || page == 0) {
			getTypesPage(id,-1,false,model);
		} else {
			getTypesPage(id,page,true,model);
			
		}
		
		model.addAttribute("types",types);
		model.addAttribute("activeTypeId",id);
		
		
		
		return "types";
	}
	
	
	public void getTypesPage(Long id,Integer page, boolean flag,Model model) {
		// 先 new Page
		Page<Blog> page1 = new Page<Blog>();
		
		
		page1.setSize(5);
		// 先查出数据条数，再计算得出多少页
		int total = blogService.getTotalAndPublishedAndTypeId(id);
		
		int totalPages;
		if (total % page1.getSize() == 0) {
			totalPages = total / page1.getSize();
		} else {
			totalPages = total / page1.getSize() + 1;
		}
		page1.setTotalPages(totalPages);
		
		if (flag) {
			page1.setPage(page);
		}
		
		page1.setContent(blogService.listBlogByTypeId(id, page1.getStart(), page1.getSize()));
		
		model.addAttribute("total", total);
		model.addAttribute("page", page1);
		
	}
	
	

}
