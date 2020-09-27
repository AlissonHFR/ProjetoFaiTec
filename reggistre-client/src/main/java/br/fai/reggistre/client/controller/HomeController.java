package br.fai.reggistre.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
	
	
	@GetMapping("/dashboard")
	public String getDashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/not-found")
	public String getNotFoundPage() {
		return "general/not-found";
	}
	
	@GetMapping("/notifications")
	public String getNotificationsPage() {
		return "notifications";
	}
	
	

}
