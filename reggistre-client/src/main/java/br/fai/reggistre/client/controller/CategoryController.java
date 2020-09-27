package br.fai.reggistre.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String getListPage(Model model) {

		List<Categoria> categoriaList = categoriaService.readAll();

		if (categoriaList != null && categoriaList.size() != 0) {
			model.addAttribute("categorias", categoriaList);

			System.out.println("Foram encontradas " + categoriaList.size() + " categorias.");

		} else {
			model.addAttribute("categorias", new ArrayList<Categoria>());

			System.out.println("Nenhuma categoria foi encontrada");
		}

		return "category/list";
	}

}
