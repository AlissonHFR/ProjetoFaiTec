package br.fai.reggistre.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@GetMapping("/detail")
	public String getDetailPage() {
		return "category/detail";
	}
	
	@GetMapping("/edit")
	public String getEditPage() {
		return "category/edit";
	}
	
	@GetMapping("/list")
	public String getListPage() {
		return "category/list";
	}
	
}
