package joao.dev.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import jef.dev.app.SpringBootApp;
import jef.dev.repository.ProdutoRepository;
import jef.dev.service.ProdutoService;
import joao.dev.test.TesteGeneric;

@AutoConfigureMockMvc
@SpringBootTest(classes = SpringBootApp.class, 
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoTest extends TesteGeneric {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private TestRestTemplate template;

	@Test
	@DisplayName("0 - Teste o ambiente de este")
	void testeInir() {
		System.out.println("Ambiente funcionando");
	}

}
