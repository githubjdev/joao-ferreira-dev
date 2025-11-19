package jef.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jef.dev.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p from Produto p where lower(p.nome) like %:nome%")
	List<Produto> buscarPorNome(@Param("nome") String nome);

}
