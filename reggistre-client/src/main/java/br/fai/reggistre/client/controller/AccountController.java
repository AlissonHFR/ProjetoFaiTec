package br.fai.reggistre.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	@GetMapping("/register")
	public String getCreateAccountPage() {
		return "account/create-account";
	}
	
	@GetMapping("/login-page")
	public String getLoginPage() {
		return "account/login";
	}
	
	@GetMapping("/password-recovery")
	public String getRecoverPasswordPage() {
		return "account/recover-password";
	}
	
	
}
