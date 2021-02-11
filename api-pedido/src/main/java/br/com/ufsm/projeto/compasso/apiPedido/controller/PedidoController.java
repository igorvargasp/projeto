package br.com.ufsm.projeto.compasso.apiPedido.controller;
import java.util.List;
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
import br.com.ufsm.projeto.compasso.apiPedido.service.PedidoService;


@RestController
public class PedidoController {

	@Autowired
	private UsuarioClient clientUser;
	
	@Autowired
	private PedidoService service;
	
	@Autowired
	private ProdutoClient clientProduto;
	
	@PostMapping(value ="/pedidos/usuario/{usuarioId}/produto/{produtoId}/quantidade/{quantidade}", produces = "application/json;charset=UTF-8")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Pedido> create(@PathVariable("usuarioId") Long usuarioId, @PathVariable("produtoId") Long produtoId,  @PathVariable("quantidade") Integer quantidade) {
		try {
			return ResponseEntity.ok(service.cadastraPedido(usuarioId, produtoId, quantidade));
		} catch (Exception e) {
			e.printStackTrace();
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
