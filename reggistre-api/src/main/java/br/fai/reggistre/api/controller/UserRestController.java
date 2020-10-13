package br.fai.reggistre.api.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import br.fai.reggistre.api.service.UserService;
import br.fai.reggistre.model.entities.PessoaFisica;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping("/minha-primeira-requisicao")
	public ResponseEntity<String> minhaPrimeiraRequisicao() {
		return ResponseEntity.ok("boa tarde");
	}

	@GetMapping("/read-all")
	public ResponseEntity<List<PessoaFisica>> readAll() {
		List<PessoaFisica> pFisicaList = userService.readAll();

		if (pFisicaList == null || pFisicaList.size() == 0) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.ok(pFisicaList);
		}

	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<PessoaFisica> readById(@PathVariable("id") Long id) {

		PessoaFisica pFisica = userService.readById(id);

		if (pFisica == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pFisica);
	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody PessoaFisica entity) {

		boolean response = userService.update(entity);

		return ResponseEntity.ok(response);

	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody PessoaFisica entity) {

		Long id = userService.create(entity);

		return ResponseEntity.ok(id);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {

		boolean response = userService.deleteById(id);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/read-by-login/{nomeUsuario}/{senha}")
	public ResponseEntity<PessoaFisica> readByLogin(@PathVariable("nomeUsuario") String nomeUsuario,
			@PathVariable("senha") String senha) throws NoSuchAlgorithmException {

//		MessageDigest md = MessageDigest.getInstance("MD5");
//		byte[] messageDigest = md.digest(senha.getBytes());
//		BigInteger no = new BigInteger(1, messageDigest);
//		String SenhaHashtext = no.toString(16);
//		while (SenhaHashtext.length() < 32) {
//			SenhaHashtext = "0" + SenhaHashtext;
//		}
		

		PessoaFisica pFisica = userService.readByLogin(nomeUsuario, senha);

		if (pFisica == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pFisica);
	}

}
