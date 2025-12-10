package jef.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jef.dev.entity.Produto;
import jef.dev.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Transactional
	public Produto atualizar(Produto p) {
		Produto existente = produtoRepository.findById(p.getId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		existente.setNome(p.getNome());
		return produtoRepository.save(existente);
	}

	@Transactional
	public void excluir(Long id) {
		produtoRepository.deleteById(id);
	}

	public List<Produto> buscarPorNome(String nome) {
		return produtoRepository.buscarPorNome(nome.toLowerCase());
	}

	public boolean existePorId(Long id) {
		return produtoRepository.existsById(id);
	}

	public List<Produto> buscarPorNomeQtd(String nome, double qtd) {
		return produtoRepository.buscarPorNomeQtd(nome.toLowerCase(), qtd);
	}
	
	
	
	public List<Produto> buscarPorNomePage(String nome, Pageable pageable) {
		return produtoRepository.buscarPorNomePage(nome, pageable);
	}
	
	
	
	public List<Produto> buscarPorPage(Pageable pageable) {
		return produtoRepository.findAll(pageable).getContent();
	}


}
