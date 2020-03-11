package com.yuer.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuer.entity.Page;
import com.yuer.entity.Type;
import com.yuer.service.ITypeService;

@Controller
@RequestMapping("/admin")
public class TypeController {

	@Autowired
	private ITypeService typeService;

	// 这个是默认的访问，不带参数
	@GetMapping("/types")
	public String typesDefault(Model model) {
		Page<Type> page = getPage(-1, false);
		model.addAttribute("page", page);

		return "admin/types";
	}

	// page代表当前页数,当按上一页下一页会触发这个方法
	@GetMapping("/types/{page}")
	public String types(@PathVariable Integer page, Model model) {
		Page<Type> page1 = getPage(page, true);
		model.addAttribute("page", page1);

		return "admin/types";
	}

	// 转到增加
	@GetMapping("/types/input")
	public String input(Model model) {

		model.addAttribute("type", new Type());
		return "admin/types-input";
	}

	// 转到编辑
	@GetMapping("/types/{id}/input")
	public String update(@PathVariable Long id, Model model) {
		Type type = typeService.getTypeById(id);

		model.addAttribute("type", type);
		return "admin/types-input";
	}

	// 删除
	@GetMapping("/types/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		typeService.deleteType(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/types";
	}

	// 增加操作
	@PostMapping("/types")
	public String save(Type type, Model model, RedirectAttributes attributes) {
		// 这里还有一个疑问，数据库中id字段不是默认的，只存name会成功吗？ 好像会，，，有默认值？
		Type t = typeService.getTypeByTypeName(type.getTypeName());
		if (t != null) {
			model.addAttribute("msg", "不能添加重复分类");
			return "admin/types-input";
		} else {
			Integer num = typeService.saveType(type);
			if (num == null || num.intValue() <= 0) {
				attributes.addFlashAttribute("message", "新增失败");
			} else {
				attributes.addFlashAttribute("message", "新增成功");
			}

			return "redirect:/admin/types";
		}

	}

	// 处理编辑
	@PostMapping("/types/{id}")
	public String edit(@PathVariable Long id, Type type, Model model, RedirectAttributes attributes) {
		Type t = typeService.getTypeByTypeName(type.getTypeName());
		if (t != null) {
			model.addAttribute("msg", "不能修改成已有分类");
			return "admin/types-input";
		} else {
			Integer num = typeService.updateType(id, type.getTypeName());
			if (num == null || num.intValue() <= 0 ) {
				attributes.addFlashAttribute("message", "更新失败");
			} else {
				attributes.addFlashAttribute("message", "更新成功");
			}

			return "redirect:/admin/types";
		}

	}

	public Page<Type> getPage(Integer page, boolean flag) {
		// 先 new Page
		Page<Type> page1 = new Page<Type>();

		// 先查出数据条数，再计算得出多少页
		int total = typeService.getTotal();
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

		page1.setContent(typeService.listTypeByParam(page1.getStart(), page1.getSize()));

		return page1;
	}

}
