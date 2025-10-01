package br.com.vinicius.projetoredis.service;


import br.com.vinicius.projetoredis.entity.Produto;
import br.com.vinicius.projetoredis.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "produtos")
public class ProdutoService {

    private final ProdutoRepository repository;

    @Cacheable(key = "#id")
    public Produto buscarPorId(Long id) {
        System.out.println("Buscando do banco de dados...");
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    @CachePut(key = "#result.id")
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    @CachePut(key = "#id")
    public Produto atualizar(Long id, BigDecimal novoPreco) {
        Produto produto = buscarPorId(id);
        produto.setPreco(novoPreco);
        return repository.save(produto);
    }

    @CacheEvict(key = "#id")
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}