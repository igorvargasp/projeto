package br.com.ufsm.projeto.compasso.apiPedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@EnableFeignClients
@SpringBootApplication
public class ApiPedido {

	public static void main(String[] args) {
		SpringApplication.run(ApiPedido.class, args);
	}

}
