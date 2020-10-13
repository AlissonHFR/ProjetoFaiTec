package br.fai.reggistre.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.fai.reggistre.api.service.MovementService;
import br.fai.reggistre.model.entities.Movimentacao;

@RestController
@RequestMapping("/api/v1/movement")
@CrossOrigin(origins = "*")
public class MovementRestController {

	@Autowired
	private MovementService movementService;
	
	@GetMapping("/read-all")
	public ResponseEntity< List<Movimentacao>> readAll(){
		
		List<Movimentacao> movimentacaoList = movementService.readAll();
		
		///
		if(movimentacaoList == null || movimentacaoList.size() == 0) {
			return ResponseEntity.ok(null);
		}else {
			return ResponseEntity.ok(movimentacaoList);
					
		}
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Movimentacao> readById(@PathVariable("id")Long id){
		
		Movimentacao movimentacao = movementService.readById(id);
		
		if(movimentacao == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(movimentacao);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Movimentacao entity){
		
		boolean response = movementService.update(entity);
		
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody Movimentacao entity){
		
		Long id = movementService.create(entity);
		
		return ResponseEntity.ok(id);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id")Long id){
		
		boolean response = movementService.deleteById(id);
		
		return ResponseEntity.ok(response);
		
	}
	
	
	
}
