package br.com.ufsm.projeto.compasso.apiPedido.controller;



import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.ufsm.projeto.compasso.apiPedido.client.ProdutoClient;
import br.com.ufsm.projeto.compasso.apiPedido.client.UsuarioClient;
import br.com.ufsm.projeto.compasso.apiPedido.model.Pedido;
import br.com.ufsm.projeto.compasso.apiPedido.model.Produto;
import br.com.ufsm.projeto.compasso.apiPedido.model.Usuario;
import br.com.ufsm.projeto.compasso.apiPedido.repository.PedidoRepository;



@RestController
public class PedidoController {
	private final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

	@Autowired
	private UsuarioClient clientUser;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoClient clientProduto;
	
	
	@PostMapping(value ="/pedidos/usuario/{usuarioId}/produto/{produtoId}/quantidade/{quantidade}", produces = "application/json;charset=UTF-8")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Pedido> create(@PathVariable("usuarioId") Long usuarioId, @PathVariable("produtoId") Long produtoId,  @PathVariable("quantidade") Integer quantidade) {
		Long id_produto = produtoId;
		Long id_usuario = usuarioId;
		Integer qtd = quantidade;
		
	
		try {
			
		
			List<Usuario> usuario = clientUser.buscaUsuario();
			
		
			try {
				LOGGER.info("Entrou no pedido ");
				Pedido pedido = new Pedido();
				for (Usuario u : usuario) {
					if(u.getId() == id_usuario) {
							
						pedido.setUsuario(u.getName());
						pedido.setIdUsuario(u.getId());
						
						LOGGER.info("Adicionou o usuario ao pedido "+ u.getId());
						
						
					
						
						}
					}
			List<Produto> produto = clientProduto.buscaProduto(); 
				for (Produto p : produto) {
					if(p.getId() == id_produto) {
						pedido.setProduto(p.getNome());
						pedido.setPreco(p.getPreco());
						pedido.setIdProduto(p.getId());				
						LOGGER.info("Adicionou o produto ao pedido "+ p.getId());
						System.out.println(p.getId());
					
					}
					pedido.setQuantidade(qtd);
					
					if(pedido.getIdUsuario().equals(null) && pedido.getIdProduto().equals(null)) {
						System.out.println("Nada foi cadastrado");
					}else {
						return ResponseEntity.ok(pedidoRepository.save(pedido));		
					}
					
				}
				
			} catch (Exception e) {
				LOGGER.info("deu erro denovo "+e);
			}
			
			
		} catch (Exception e) {
			LOGGER.info("Pedido erro " + e);
		}
	
		return ResponseEntity.notFound().build();
		
	};
	
	@GetMapping("/usuario")
	public List<Usuario> buscaUser() {
			
	return clientUser.buscaUsuario();	
	}
	
	@GetMapping("/produto")
	public List<Produto> buscaProd() {
	return clientProduto.buscaProduto();
	}
	
}
