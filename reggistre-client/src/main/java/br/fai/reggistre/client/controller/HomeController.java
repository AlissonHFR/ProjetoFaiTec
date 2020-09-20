package br.fai.reggistre.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping("/not-found")
	public String getNotFoundPage() {
		return "general/not-found";
	}
	

}
