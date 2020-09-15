package br.fai.reggistre.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.reggistre.client.service.UserService;
import br.fai.reggistre.model.entities.PessoaFisica;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/detail/{id}")
	public String getDetailPage(@PathVariable("id") Long id, Model model) {
	
		PessoaFisica pFisica = userService.readById(id);
		
		model.addAttribute("pFisica", pFisica);
		
		return "user/detail";
	}
	
	@GetMapping("/edit")
	public String getEditPage() {
		return "user/edit";
	}
	
	@GetMapping("/list")
	public String getListPage(Model model) {
		
		List<PessoaFisica> pFisicasList  = userService.readAll();
		
		if(pFisicasList!=null && pFisicasList.size() !=0) {
			model.addAttribute("pFisicasList", pFisicasList);
			
			System.out.println("foram encontrados " + pFisicasList.size() + " usuarios.");
		}else {
			model.addAttribute("pFisicasList", new ArrayList<PessoaFisica>());
			System.out.println("nenhum usuario foi encontrado");
		}
		
		return "user/list";
	}
	
	@GetMapping("/create")
	public String getCreatePage() {
		
		return "user/create";
	}
	
	
	
}
