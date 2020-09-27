package br.fai.reggistre.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.reggistre.client.service.MovementService;
import br.fai.reggistre.model.entities.Movimentacao;

@Controller
@RequestMapping("/movement")
public class MovementController {

	@Autowired
	private MovementService movementService;

	@GetMapping("/detail/{id}")
	public String getDetailPage(@PathVariable("id") Long id, Model model) {

		Movimentacao movimentacao = movementService.readById(id);

		model.addAttribute("movimentacao", movimentacao);

		return "movement/detail";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") Long id, Model model) {

		Movimentacao movimentacao = movementService.readById(id);

		model.addAttribute("movimentacao", movimentacao);

		return "movement/edit";
	}

	@GetMapping("/list")
	public String getListPage(Model model) {

		List<Movimentacao> movimentacaoList = movementService.readAll();

		if (movimentacaoList != null && movimentacaoList.size() != 0) {
			model.addAttribute("movimentacao", movimentacaoList);
			System.out.println("foram encontrados" + movimentacaoList.size() + "movimentações");

		} else {
			model.addAttribute("movimentacao", new ArrayList<Movimentacao>());
			System.out.println("Nenhuma movimentacao foi encontrada");
		}

		return "movement/list";
	}

}
