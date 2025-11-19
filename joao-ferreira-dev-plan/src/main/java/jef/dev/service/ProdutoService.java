package jef.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jef.dev.entity.Produto;
import jef.dev.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public List<Produto> listarTodos() {
		return repository.findAll();
	}

	public Optional<Produto> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

	@Transactional
	public Produto atualizar(Produto p) {
		Produto existente = repository.findById(p.getId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		existente.setNome(p.getNome());
		return repository.save(existente);
	}

	@Transactional
	public void excluir(Long id) {
		repository.deleteById(id);
	}

	@Transactional
	public Optional<List<Produto>> buscarPorNome(String nome) {
		return repository.buscarPorNome(nome);
	}
	
	@Transactional
	public Optional<List<Produto>> buscarPorNome(String nome, int pagina) {
		Pageable page = PageRequest.of(pagina, 5);
		return repository.buscarPorNome(nome, page);
	}
	
	@Transactional
	public boolean existePorId(Long id) {
	    return repository.existsById(id);
	}

}
