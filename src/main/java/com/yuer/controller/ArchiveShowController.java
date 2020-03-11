package com.yuer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yuer.service.IBlogService;

@Controller
public class ArchiveShowController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
    	model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("total", blogService.getTotalAndPublished());
    	
    	
        return "archives";
    }
}