package br.fai.reggistre.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	int erroLogin;

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}

	@GetMapping("/dashboard")
	public ModelAndView getDashboardPage(HttpSession session) throws Exception {
		ModelAndView mv = null;

		if (session.getAttribute("usuarioLogado") == null) {

			mv = new ModelAndView("redirect:/user/login");
			erroLogin = 1;
			return mv;
		} else {

			mv = new ModelAndView("/dashboard");
			mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		}
		return mv;
	}

	@GetMapping("/not-found")
	public String getNotFoundPage() {
		return "general/not-found";
	}

	@GetMapping("/notifications")
	public ModelAndView getNotificationsPage(HttpSession session) {
		ModelAndView mv = null;

		mv = new ModelAndView("notifications");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));

		return mv;
	}

}
