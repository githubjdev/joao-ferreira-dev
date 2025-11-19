package jef.dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jef.dev.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Transactional(readOnly = true)
	@Query("select p from Produto p where p.nome like %:nome%")
	Optional<List<Produto>> buscarPorNome(@Param("nome") String nome);

	@Transactional(readOnly = true)
	@Query("select p from Produto p where lower(p.nome) like lower(concat('%', :nome, '%'))")
	Optional<List<Produto>> buscarPorNome(@Param("nome") String nome, Pageable pageable);

}