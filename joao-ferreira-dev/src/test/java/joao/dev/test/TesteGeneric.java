package joao.dev.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import lombok.Generated;

@Generated
public class TesteGeneric {

	@Autowired
	private WebApplicationContext web;

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Value("${server.servlet.context-path}")
	private String context;

	@Value("${spring.web.url.hostname}")
	private String hostname;

	/**
	 * 
	 * @param api/produtos
	 * @return URL
	 */
	// http://localhost:8080/joao-ferreira-dev/api/produtos
	protected String url(String endPoint) {
		return hostname + ":" + port + hostname + "/" + endPoint;
	}

}
