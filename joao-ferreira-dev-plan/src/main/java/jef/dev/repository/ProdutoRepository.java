package jef.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jef.dev.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}