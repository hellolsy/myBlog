package com.yuer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuer.entity.Blog;
import com.yuer.entity.Page;
import com.yuer.entity.Tag;
import com.yuer.entity.Temp;
import com.yuer.entity.Type;
import com.yuer.service.IBlogService;
import com.yuer.service.ITagService;
import com.yuer.service.ITypeService;

@Controller
@RequestMapping
public class IndexController {
	
	@Autowired
	private IBlogService blogService;
	
	@Autowired
	private ITypeService typeService;
	
	@Autowired
	private ITagService tagService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		
		getPage(-1, false,model);
		getTopOther(model);
		
		
		return "index";
	}
	
	// 这个是点击翻页后触发的
	@GetMapping("/{page}")
	public String index1(@PathVariable Integer page, Model model) {
		getPage(page, true,model);
		getTopOther(model);
		
		return "index";
	}
	
	@PostMapping("/search")
	public String search(String query, Model model) {
		if (query == null || "".equals(query)) {
			return "index";
		}
		getSearchPage(query,-1,false,model);
		model.addAttribute("query", query);
		// 这里使用的是Temp存储query
		Temp.query = query;
		
		
		return "search";
	}
	
	@GetMapping("/search/{page}")
	public String search1(@PathVariable Integer page, Model model) {
		getSearchPage(Temp.query,page,true,model);
		model.addAttribute("query", Temp.query);
		
		return "search";
	}
	
	@GetMapping("/blog/{id}")
	public String blog(@PathVariable Long id, Model model) {
		// 还有要使用th:utext以免html元素没有显示出该有的效果
		model.addAttribute("blog", blogService.getAndConvert(id));

		return "blog";
	}
	
	@GetMapping("/footer/newblog")
	public String newblogs(Model model) {
		model.addAttribute("newblogs", blogService.listBlogTop(3));
		return "_fragments :: newblogList";
	}
	
	
	
	
	
	
	// 获取top的type和tag,最新推荐
	public void getTopOther(Model model) {
		List<Type> types = typeService.listTypeTop(6);
		List<Tag> tags = tagService.listTagTop(10);
		
		// 这个是根据发布状态和更新时间,还有是否推荐
		List<Blog> blogs = blogService.listBlogTop(8);
		
		model.addAttribute("types", types);
		model.addAttribute("tags", tags);
		model.addAttribute("blogs", blogs);
	}
	
	
	public void getPage(Integer page, boolean flag,Model model) {
		// 先 new Page
		Page<Blog> page1 = new Page<Blog>();

		
		page1.setSize(7);
		// 先查出数据条数，再计算得出多少页
		int total = blogService.getTotalAndPublished();
		
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
		
		page1.setContent(blogService.listBlogByParamAndPublished(page1.getStart(), page1.getSize()));

		model.addAttribute("total", total);
		model.addAttribute("page", page1);
		
	}
	
	public void getSearchPage(String query, Integer page, boolean flag,Model model) {
		// 先 new Page
		Page<Blog> page1 = new Page<Blog>();
		
		
		page1.setSize(7);
		// 先查出数据条数，再计算得出多少页
		int total = blogService.getTotalAndPublishedAndSearch(query);
		
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
		
		page1.setContent(blogService.listBlogBySerach(query, page1.getStart(), page1.getSize()));
		
		model.addAttribute("total", total);
		model.addAttribute("page", page1);
		
	}
	
	
	
	

}
