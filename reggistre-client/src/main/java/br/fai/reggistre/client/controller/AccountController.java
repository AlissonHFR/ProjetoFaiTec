package br.fai.reggistre.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.reggistre.model.entities.PessoaFisica;



@Controller
@RequestMapping("/account")
public class AccountController {

	@GetMapping("/register")
	public String getCreateAccountPage(PessoaFisica pFisica) {
		return "account/create-account";
	}
	
	@GetMapping("/login-page")
	public String getLoginPage(PessoaFisica pFisica) {
		return "account/login";
	}
	
	@GetMapping("/password-recovery")
	public String getRecoverPasswordPage() {
		return "account/recover-password";
	}
	
	
}
