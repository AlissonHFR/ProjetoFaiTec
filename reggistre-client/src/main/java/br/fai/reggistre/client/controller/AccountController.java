package br.fai.reggistre.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.fai.reggistre.client.service.CategoryService;
import br.fai.reggistre.client.service.UserService;
import br.fai.reggistre.model.entities.Movimentacao;
import br.fai.reggistre.model.entities.PessoaFisica;



@Controller
@RequestMapping("/account")
public class AccountController {

	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public ModelAndView getCreateAccountPage(PessoaFisica pFisica) {	
		ModelAndView mv = null;		
		List<PessoaFisica> pfList = new ArrayList<>();		
		List<String> pfListUsuarios = new ArrayList<>();
		pfList = userService.readAll();
		
		for (PessoaFisica pessoaFisica : pfList)
		{
			pfListUsuarios.add(pessoaFisica.getNomeUsuario());
		}
		mv = new ModelAndView("account/create-account");
		mv.addObject("pfList", pfListUsuarios);
		return mv;
		
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
