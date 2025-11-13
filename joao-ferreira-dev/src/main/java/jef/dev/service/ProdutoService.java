package jef.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jef.dev.entity.Produto;
import jef.dev.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Produto criarSalvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	
	@Transactional
	public Produto atualizar (Produto p) {
		produtoRepository.
				findById(p.getId())
				.orElseThrow(() -> new RuntimeException("Produto não existe"));
		
		
		return produtoRepository.save(p);
	}

}
