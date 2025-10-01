package br.com.vinicius.projetoredis.repository;

import br.com.vinicius.projetoredis.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
