package br.com.ufsm.projeto.compasso.produto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiProduto {

	public static void main(String[] args) {
		SpringApplication.run(ApiProduto.class, args);
	}

}
