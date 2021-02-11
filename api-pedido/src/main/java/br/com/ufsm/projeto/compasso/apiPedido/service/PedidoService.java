package br.com.ufsm.projeto.compasso.apiPedido.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ufsm.projeto.compasso.apiPedido.client.ProdutoClient;
import br.com.ufsm.projeto.compasso.apiPedido.client.UsuarioClient;
import br.com.ufsm.projeto.compasso.apiPedido.model.Pedido;
import br.com.ufsm.projeto.compasso.apiPedido.model.Produto;
import br.com.ufsm.projeto.compasso.apiPedido.model.Usuario;
import br.com.ufsm.projeto.compasso.apiPedido.repository.PedidoRepository;

@Service
public class PedidoService {
    
    private final Logger LOGGER = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
	private UsuarioClient clientUser;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoClient clientProduto;

    public Pedido cadastraPedido(Long usuarioId, Long produtoId, Integer quantidade){       
		
	
	Integer qtd = quantidade;
	try {
		List<Usuario> usuario = clientUser.buscaUsuario();
		try {
			LOGGER.info("Entrou no pedido");
			Pedido pedido = new Pedido();
			
			for (Usuario u : usuario) {
				if(u.getId() == usuarioId) {			
					pedido.setUsuario(u.getNome());
					pedido.setIdUsuario(u.getId());
					LOGGER.info("Adicionou o usuario ao pedido " + u.getId());
				}
			}
			List<Produto> produto = clientProduto.buscaProduto(); 
			for (Produto p : produto) {
				if(p.getId() == produtoId) {
					
					if(p.getDisponivel().equals(false)){
						LOGGER.info("Produto indisponivel " + p.getId());
						return null;
					}else{
						pedido.setQuantidade(qtd);
						pedido.setProduto(p.getNome());
						pedido.setPreco(p.getPreco());
						pedido.setIdProduto(p.getId());				
						LOGGER.info("Adicionou o produto ao pedido " + p.getId());
						return pedidoRepository.save(pedido);
					}
											
				}
				
				
			}
		} catch (Exception e) {
			LOGGER.info("Erro ao fazer pedido " + e);
		}
	} catch (Exception e) {
		LOGGER.info("Pedido erro " + e);
	}
	LOGGER.info("Nada foi cadastrado");




	return null;
       

    }
}
