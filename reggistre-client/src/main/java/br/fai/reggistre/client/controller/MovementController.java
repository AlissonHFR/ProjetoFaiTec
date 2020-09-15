package br.fai.reggistre.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movement")
public class MovementController {

	@GetMapping("/detail")
	public String getDetailPage() {
		return "movement/detail";
	}
	
	@GetMapping("/edit")
	public String getEditPage() {
		return "movement/edit";
	}
	
	@GetMapping("/list")
	public String getListPage() {
		return "movement/list";
	}
	
}
