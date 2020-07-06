package com.seguridad.autenticacion;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.seguridad.dto.bienvenida.BienvenidaRequestDTO;

/**
 * Test para el proceso de negocio para obtener los datos de bienvenida
 * http://localhost:port/auth/bienvenida
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GetDatosBienvenidaTest {

	/** Es un random puerto para hacer el llamado a los rest */
//	@LocalServerPort
//	private int port;

	/** Se utiliza para ser las invocaciones REST */
//	@Autowired
//	private TestRestTemplate template;

	/**
	 * Metodo test para validar el flujo basico para obtener los datos bienvenida
	 */
	@Test
	public void getDatosBienvenidaExitoso() throws Exception {
/*		
		// url del recurso para obtener los datos de la bienvenida
		String url = "http://localhost:" + this.port + "/auth/bienvenida/";

		// se construye el request
		Long idUsuario = 1l;
		BienvenidaRequestDTO data = new BienvenidaRequestDTO();
		data.setIdUsuario(idUsuario);
		ResponseEntity<Object> result = this.template.postForEntity(url, data, Object.class);

		// el response debe retornar OK con los datos de la bienvenida
		Assert.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
*/		
	}
}
