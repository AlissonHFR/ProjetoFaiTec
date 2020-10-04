package br.fai.reggistre.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.fai.reggistre.client.service.UserService;
import br.fai.reggistre.client.service.impl.UserServiceImpl;
import br.fai.reggistre.model.entities.PessoaFisica;

@Controller
@RequestMapping("/user")
public class UserController {
	int erroLogin;
	@Autowired
	private UserService userService;

	@GetMapping("/detail/{id}")
	public ModelAndView getDetailPage(@PathVariable("id") Long id, Model model, HttpSession session) {
		// PessoaFisica pFisica = userService.readById(id);
		ModelAndView mv = null;

		mv = new ModelAndView("user/detail");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));

		return mv;
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") Long id, Model model, HttpSession session) {

		// PessoaFisica pFisica = userService.readById(id);

		model.addAttribute("usuarioLogado", session.getAttribute("usuarioLogado"));

		return "user/edit";

	}

	@GetMapping("/list")
	public String getListPage(Model model) {

		List<PessoaFisica> pFisicasList = userService.readAll();

		if (pFisicasList != null && pFisicasList.size() != 0) {
			model.addAttribute("pFisicasList", pFisicasList);

			System.out.println("foram encontrados " + pFisicasList.size() + " usuarios.");
		} else {
			model.addAttribute("pFisicasList", new ArrayList<PessoaFisica>());
			System.out.println("nenhum usuario foi encontrado");
		}

		return "user/list";
	}

	@PostMapping("/create")
	public ModelAndView getCreatePage(PessoaFisica pFisica, Model model, HttpSession session) {
		Long id = userService.create(pFisica);
		ModelAndView mv = null;
		if (id == 0) {
			mv = new ModelAndView("redirect:/account/register?erroServidor");
			return mv;
		}

		pFisica.setId(id);
		model.addAttribute("pFisica", pFisica);

		return getDetailPage(pFisica.getId(), model, session);
	}

	@PostMapping("/update")
	public ModelAndView update(PessoaFisica pFisica, Model model, HttpSession session) {
		ModelAndView mv = null;

		userService.update(pFisica);
		pFisica = userService.readById(pFisica.getId());
		session.setAttribute("usuarioLogado", pFisica);
		mv = new ModelAndView("redirect:/dashboard");

		return mv;
		// return getDetailPage(pFisica.getId(), model, session);

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getFormLogin() throws Exception {
		ModelAndView mv = null;

		mv = new ModelAndView("account/login");
		mv.addObject("ErroLogin", erroLogin); // Caso queira controlar quando user errar a senha 1 vez
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpSession session, String username, String pass) throws Exception {
		ModelAndView mv = null;
		UserServiceImpl us = new UserServiceImpl();

		PessoaFisica usuario = us.readByLogin(username, pass);

		session.setAttribute("usuarioLogado", usuario);

		mv = new ModelAndView("redirect:/dashboard");

		return mv;
	}

	// Parte do login
	@RequestMapping(value = "/usuarioLogado", method = RequestMethod.GET)
	public ModelAndView getUsuarioLogado(HttpSession session) throws Exception {
		ModelAndView mv = null;

	if(session.getAttribute("usuarioLogado")==null)
	{

		mv = new ModelAndView("redirect:/user/login");
		erroLogin = 1;
		return mv;
	}else
	{

		mv = new ModelAndView("/user/usuarioLogado");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
	}return mv;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        ModelAndView mv = null;
        if (session.getAttribute("usuarioLogado") != null) {
            session.invalidate();
            mv = new ModelAndView("redirect:/user/login");
        } else {
            mv = new ModelAndView("redirect:/user/login");
        }

        return mv;
    }

}
