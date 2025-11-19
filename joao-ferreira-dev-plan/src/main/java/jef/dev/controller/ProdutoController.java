package jef.dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jef.dev.entity.Produto;
import jef.dev.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos() {
		return ResponseEntity.ok(service.listarTodos());
	}

	@PostMapping
	public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
		Produto salvo = service.salvar(produto);
		return ResponseEntity.ok(salvo);
	}

	@PutMapping
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
		return ResponseEntity.ok(service.atualizar(produto));
	}

	/*Pendente de ser passado para o aluno*/
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
		
	    if (id <= 0) {
	        return ResponseEntity.badRequest().build();
	    }
		
	    return service.buscarPorId(id)
	            .map(ResponseEntity::ok)
	            .orElseGet(() -> ResponseEntity.notFound().build());
	}

	
	/*Pendente de ser passado para o aluno*/
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

	    if (!service.existePorId(id)) {
	        return ResponseEntity.notFound().build();
	    }

	    service.excluir(id);
	    return ResponseEntity.noContent().build();
	}

	
	/*Pendente de ser passado para o aluno*/
	@GetMapping("buscarPorNome/{nome}")
	public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable String nome) {
	    List<Produto> produtos = service.buscarPorNome(nome).get();

	    if (produtos.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(produtos);
	}
	
	
	/*Pendente de ser passado para o aluno*/
	@GetMapping("/buscarPorNome/{nome}/{pagina}")
	public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable String nome, int pagina) {

		Optional<List<Produto>> produtos = service.buscarPorNome(nome, pagina);

		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(produtos.get());
	}



}
