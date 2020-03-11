package com.yuer.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuer.entity.Blog;
import com.yuer.entity.BlogQuery;
import com.yuer.entity.Page;
import com.yuer.entity.Temp;
import com.yuer.entity.Type;
import com.yuer.entity.User;
import com.yuer.service.IBlogService;
import com.yuer.service.ITagService;
import com.yuer.service.ITypeService;

@Controller
@RequestMapping("/admin")
public class BlogController {

	@Autowired
	private IBlogService blogService;

	@Autowired
	private ITypeService typeService;

	@Autowired
	private ITagService tagService;

	// 这个是默认的访问，不带参数
	@GetMapping("/blogs")
	public String blogsDefault(Model model) {
		Page<Blog> page = getPage(-1, false);

		// 查出所有的type
		List<Type> types = typeService.listType();
		model.addAttribute("page", page);
		model.addAttribute("types", types);

		return "admin/blogs";
	}

	// 点击翻页或搜索时触发
	@PostMapping("/blogs/search")
	public String blogs(Integer page, BlogQuery blog, Model model) {
		if (blog == null) {
			Page<Blog> page1 = getPage(page, true);
			model.addAttribute("page", page1);
		} else {
			if (page == null || page.intValue() <= 0) {
				Page<Blog> page1 = getPageByQuery(blog, page, false);
				model.addAttribute("page", page1);

			} else {
				Page<Blog> page1 = getPageByQuery(blog, page, true);
				model.addAttribute("page", page1);
			}
		}

		return "admin/blogs :: blogList";
	}

	// 转到增加
	@GetMapping("/blogs/input")
	public String input(Model model) {
		// 还得传types和tags
		setTypeAndTag(model);

		model.addAttribute("blog", new Blog());

		return "admin/blogs-input";
	}

	// 转到编辑
	@GetMapping("/blogs/{id}/input")
	public String update(@PathVariable Long id, Model model) {
		setTypeAndTag(model);
		Blog blog = blogService.getBlogById(id);
		Temp.createTime = blog.getCreateTime();
		// 这里还得从数据库中找出相应得标签注入值
		List<Long> list = blogService.getTags(id);
		// 还有初始化一下tagIds(如果使用的jpa或hibernate就会将此处的set注入数据库中)
		blog.setTagIds(tagsToIds(list));

		model.addAttribute("blog", blog);
		return "admin/blogs-input";
	}

	// 删除
	@GetMapping("/blogs/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		blogService.deleteBlog(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/blogs";
	}

	// 增加操作/编辑，区分在id是否为空
	@PostMapping("/blogs")
	public String save(Blog blog, Model model, RedirectAttributes attributes, HttpSession session) {
		// 设置一些编写博客保存不了的值
		User user = new User();
		user.setId(((User) session.getAttribute("user")).getId());
		blog.setUser(user);

		// 不为空代表在修改
		if (Temp.createTime != null) {
			blog.setCreateTime(Temp.createTime);
		}

		// 不推荐不开启那些得加一个判断
		if (blog.getIsRecommend() == null) {
			blog.setIsRecommend(false);
		}
		if (blog.getIsAppreciate() == null) {
			blog.setIsAppreciate(false);
		}
		if (blog.getIsComment() == null) {
			blog.setIsComment(false);
		}
		if (blog.getIsOpenCopyright() == null) {
			blog.setIsOpenCopyright(false);
		}

		// 保存完blog后再将这个blog的id取出再在对应的博客和标签的表中插入数据
		Integer num = blogService.saveBlog(blog);
		if (num == null || num.intValue() <= 0) {
			attributes.addFlashAttribute("message", "操作失败");
		} else {
			attributes.addFlashAttribute("message", "操作成功");
		}

		return "redirect:/admin/blogs";

	}


	public Page<Blog> getPage(Integer page, boolean flag) {
		// 先 new Page
		Page<Blog> page1 = new Page<Blog>();

		page1.setSize(4);
		// 先查出数据条数，再计算得出多少页
		int total = blogService.getTotal();
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

		page1.setContent(blogService.listBlogByParam(page1.getStart(), page1.getSize()));

		return page1;
	}

	public Page<Blog> getPageByQuery(BlogQuery blog, Integer page, boolean flag) {
		// 先 new Page
		Page<Blog> page1 = new Page<Blog>();

		page1.setSize(4);
		// 先查出数据条数，再计算得出多少页
		int total = blogService.getTotalByParams(blog);
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

		page1.setContent(blogService.getBlogByParams(blog, page1.getStart(), page1.getSize()));

		return page1;

	}

	public void setTypeAndTag(Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("tags", tagService.listTag());
	}
	
	// 将数据库取出的值的tagIds重新提取出放入tagIds，组成类似1，2，3的字符串
	private String tagsToIds(List<Long> tagIds) {
		if (!tagIds.isEmpty()) {
			StringBuffer ids = new StringBuffer();
			boolean flag = false;
			for (Long id : tagIds) {
				if (flag) {
					ids.append(",");
				} else {
					flag = true;
				}
				ids.append(id);
			}
			return ids.toString();
		} else {
			return "";
		}
	}

}
