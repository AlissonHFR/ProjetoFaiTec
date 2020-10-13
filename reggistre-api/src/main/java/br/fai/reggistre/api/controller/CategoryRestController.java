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

import br.fai.reggistre.api.service.CategoryService;
import br.fai.reggistre.model.entities.Categoria;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "*")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/read-all")
	public ResponseEntity< List<Categoria>> readAll(){
		
		List<Categoria> categoriaList = categoryService.readAll();
		
		if(categoriaList == null || categoriaList.size() == 0) {
			return ResponseEntity.ok(null);
		}else {
			return ResponseEntity.ok(categoriaList);
					
		}
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Categoria> readById(@PathVariable("id")Long id){
		
		Categoria categoria = categoryService.readById(id);
		
		if(categoria == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(categoria);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Categoria entity){
		
		boolean response = categoryService.update(entity);
		
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody Categoria entity){
		
		Long id = categoryService.create(entity);
		
		return ResponseEntity.ok(id);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id")Long id){
		
		boolean response = categoryService.deleteById(id);
		
		return ResponseEntity.ok(response);
		
	}
}
