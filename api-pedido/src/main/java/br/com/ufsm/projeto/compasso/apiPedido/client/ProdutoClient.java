package br.com.ufsm.projeto.compasso.apiPedido.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ufsm.projeto.compasso.apiPedido.model.Produto;

@FeignClient(name = "produto", url = "http://localhost:8081/produto")
public interface ProdutoClient {

	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> buscaProduto();

	@PutMapping(value = "/{id}/{qtd}")
	public Produto AtualizaQuantidade(@PathVariable("id") Long id, @PathVariable("qtd") int qtd);

}
