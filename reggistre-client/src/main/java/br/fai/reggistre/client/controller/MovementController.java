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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.fai.reggistre.client.service.CategoryService;
import br.fai.reggistre.client.service.MovementService;
import br.fai.reggistre.model.entities.Categoria;
import br.fai.reggistre.model.entities.Movimentacao;


@Controller
@RequestMapping("/movement")
public class MovementController {

	@Autowired
	private MovementService movementService;
	
	@Autowired
	private CategoryService categoriaService;

	@GetMapping("/detail/{id}")
	public ModelAndView getDetailPage(@PathVariable("id") Long id, Model model,HttpSession session) {
		
		
		ModelAndView mv = null;
		if (session.getAttribute("usuarioLogado") == null)
		{
			mv = new ModelAndView("redirect:/user/login");	
			return mv;
		}
		Movimentacao movimentacao = movementService.readById(id);
		mv = new ModelAndView("movement/detail");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		

		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView getEditPage(@PathVariable("id") Long id, Model model,HttpSession session) {
		ModelAndView mv = null;
		if (session.getAttribute("usuarioLogado") == null)
		{
			mv = new ModelAndView("redirect:/user/login");	
			return mv;
		}
		Movimentacao movimentacao = movementService.readById(id);
		Categoria categoria = categoriaService.readById(movimentacao.getCategoriaId());
		model.addAttribute("movimentacao", movimentacao);
		model.addAttribute("categoria", categoria);
		
		mv = new ModelAndView("/movement/edit");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));

		return mv;
	}

	@GetMapping("/list")
	public ModelAndView getListPage(Model model,HttpSession session) {
		
		ModelAndView mv = null;
		if (session.getAttribute("usuarioLogado") == null)
		{
			mv = new ModelAndView("redirect:/user/login");	
			return mv;
		}
		List<Movimentacao> movimentacaoList = movementService.readAll();
		
		mv = new ModelAndView("movement/list");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));

		
		if (movimentacaoList != null && movimentacaoList.size() != 0) {
			model.addAttribute("movimentacao", movimentacaoList);
			System.out.println("foram encontrados " + movimentacaoList.size() + " movimentações");

		} else {
			model.addAttribute("movimentacao", new ArrayList<Movimentacao>());
			System.out.println("Nenhuma movimentacao foi encontrada");
		}

		return mv;
	}
	
	@PostMapping("/update")
	public ModelAndView update(Movimentacao movimentacao, Model model,HttpSession session) {
		ModelAndView mv = null;
		
		
		if (session.getAttribute("usuarioLogado") == null)
		{
			mv = new ModelAndView("redirect:/user/login");	
			return mv;
		}
		
		Movimentacao mAntesUpdate = movementService.readById(movimentacao.getId());
		
		movementService.update(movimentacao);
		movimentacao = movementService.readById(movimentacao.getId());
		
		mv = new ModelAndView("redirect:/movement/list");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		
		return mv;
	}
	
	@PostMapping("/create")
	public ModelAndView create(Movimentacao movimentacao,Categoria categoria, Model model, HttpSession session) {
		
		ModelAndView mv = null;
		if (session.getAttribute("usuarioLogado") == null)
		{
			mv = new ModelAndView("redirect:/user/login");	
			return mv;
		}
		Long id = movementService.create(movimentacao);

		
		movimentacao.setId(id);
		model.addAttribute("movimentacao", movimentacao);
		mv = new ModelAndView("redirect:/movement/list");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		
		return mv;
	}
	
		
	@GetMapping("/register")
	public String getCreateMovimentacao(Movimentacao movimentacao,Categoria categoria,Model model,HttpSession session) {
	    
		if (session.getAttribute("usuarioLogado") == null)
		{
			
			return "redirect:/user/login";
		}
	    
	    List<Categoria> categoriaList = categoriaService.readAll();
	    
	    if (categoriaList != null && categoriaList.size() != 0) {
	        model.addAttribute("categorias", categoriaList);
	        System.out.println("foram encontradas" + categoriaList.size() + "categorias");

	    } else {
	        model.addAttribute("categorias", new ArrayList<Categoria>());
	        System.out.println("Nenhuma categoria foi encontrada");
	    }
	    


	    model.addAttribute("usuarioLogado", session.getAttribute("usuarioLogado"));
	   // model.addAttribute("categoriaSelecionada", categoria.getId());
	    
	    return "movement/create";
	}
	
	
	@GetMapping("/delete/{id}")
	public ModelAndView getDeletePage(@PathVariable("id") Long id, Model model,HttpSession session) {
		ModelAndView mv = null;
		if (session.getAttribute("usuarioLogado") == null)
		{
			mv = new ModelAndView("redirect:/user/login");	
			return mv;
		}
		movementService.deleteById(id);

		mv = new ModelAndView("redirect:/movement/list");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		

		return mv;
	}
	

}
