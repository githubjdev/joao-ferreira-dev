package jef.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos() {
		return ResponseEntity.ok(produtoService.listarTodos());
	}

	@PostMapping
	public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
		Produto p = produtoService.criarSalvar(produto);
		return ResponseEntity.ok(p);

	}

	@PutMapping
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
		return ResponseEntity.ok(produtoService.atualizar(produto));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable(name="id") Long id){
		
		if (id < 0) {
			return ResponseEntity.badRequest().build();
		}
		
		return produtoService.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir (@PathVariable(name = "id") Long id){
	     
		if(!produtoService.existePorIr(id)) {
			return ResponseEntity.notFound().build();
		}
		
		produtoService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("buscarPorNome/{nome}")
	public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable(name = "nome") String nome){
		
		List<Produto> produtos = produtoService.buscarPorNome(nome);
		
		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
		
	}
	
	
	/*Buscar por nome e quantide*/
	

}
