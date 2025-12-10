package jef.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

/**
 * http://localhost:8080/joao-ferreira-dev/api/produtos
 */

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	/* Passado para o aluno */
	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos() {
		return ResponseEntity.ok(produtoService.listarTodos());
	}

	/* Passado para o aluno */
	@PostMapping
	public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
		Produto salvo = produtoService.salvar(produto);
		return ResponseEntity.ok(salvo);
	}

	/* Passado para o aluno */
	@PutMapping
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
		return ResponseEntity.ok(produtoService.atualizar(produto));
	}

	/* Passado para o aluno */
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable(name = "id") Long id) {

		if (id <= 0) {
			return ResponseEntity.badRequest().build();
		}

		return produtoService.buscarPorId(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/* Passado para o aluno */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable(name = "id") Long id) {

		if (!produtoService.existePorId(id)) {
			return ResponseEntity.notFound().build();
		}

		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	/* Passado para o aluno */
	@GetMapping("buscarPorNome/{nome}")
	public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable(name = "nome") String nome) {
		List<Produto> produtos = produtoService.buscarPorNome(nome);

		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(produtos);
	}

	/* Falta passar para o aluno e corrogir como tarefa da aula anterior */
	@GetMapping("buscarPorNomeQtd/{nome}/{qtd}")
	public ResponseEntity<List<Produto>> buscarPorNomeQtd(@PathVariable(name = "nome") String nome,
			@PathVariable(name = "qtd") double qtd) {
		List<Produto> produtos = produtoService.buscarPorNomeQtd(nome.toLowerCase(), qtd);

		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(produtos);
	}
	
	
	/* Falta passar para o aluno */
	@GetMapping("buscarPorNomePage/{nome}/{page}")
	public ResponseEntity<List<Produto>> buscarPorNomePage(@PathVariable(name = "nome") String nome,
														@PathVariable(name = "page") int page) {
		
		
		List<Produto> produtos = produtoService.buscarPorNomePage(nome.toLowerCase(),
				                                                 PageRequest.of(page, 5));

		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(produtos);
	}
	
	
	/* Falta passar para o aluno */
	@GetMapping("buscarPorPage/{page}/{qtd}")
	public ResponseEntity<List<Produto>> buscarPorPage(@PathVariable(name = "page") int page,
			                                          @PathVariable(name = "qtd") int qtd) {
		
		
		List<Produto> produtos = produtoService.buscarPorPage(PageRequest.of(page, qtd));

		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(produtos);
	}
	
	

}
