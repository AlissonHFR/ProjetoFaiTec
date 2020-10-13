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
import br.fai.reggistre.model.entities.Categoria;



@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoriaService;

	@GetMapping("/detail/{id}")
	public String getDetailPage(@PathVariable("id") Long id, Model model) {

		Categoria categoria = categoriaService.readById(id);

		model.addAttribute("categoria", categoria);

		return "category/detail";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") Long id, Model model) {

		Categoria categoria = categoriaService.readById(id);

		model.addAttribute("categoria", categoria);

		return "category/edit";
	}

	@GetMapping("/list")
	public ModelAndView getListPage(Model model,HttpSession session) {
		ModelAndView mv = null;
		List<Categoria> categoriaList = categoriaService.readAll();
		
		mv = new ModelAndView("category/list");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));

		
		if (categoriaList != null && categoriaList.size() != 0) {
			model.addAttribute("categoria", categoriaList);
			System.out.println("foram encontradas" + categoriaList.size() + "categorias");

		} else {
			model.addAttribute("categoria", new ArrayList<Categoria>());
			System.out.println("Nenhuma categoria foi encontrada");
		}

		return mv;
	}
	
	@PostMapping("/update")
	public String update(Categoria categoria,Model model,HttpSession session) {
		categoriaService.update(categoria);
		
		return getDetailPage(categoria.getId(), model);
		
	}
	@PostMapping("/create")
	public ModelAndView create(Categoria categoria,Model model,HttpSession session) {
		Long id = categoriaService.create(categoria);
		ModelAndView mv = null;
		
		if(id == 0) {
			mv = new ModelAndView("redirect:/category/resgiter?serverError");
			return mv;
		}
		
		categoria.setId(id);
		
		model.addAttribute("categoria",categoria);
		
		mv = new ModelAndView("redirect:/category/list");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		
		return mv;
		
	}
	
	@GetMapping("/register")
	public ModelAndView getCreateMovimentcao(Categoria categoria,HttpSession session) {
		ModelAndView mv = null;
		
		mv = new ModelAndView("category/create");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView getDeletePage(@PathVariable("id") Long id, Model model,HttpSession session) {
		ModelAndView mv = null;
		categoriaService.deleteById(id);

		mv = new ModelAndView("redirect:/category/list");
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		

		return mv;
	}

}
