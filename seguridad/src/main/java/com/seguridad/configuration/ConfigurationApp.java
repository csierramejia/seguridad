package com.seguridad.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuraciones generales de spring boot, donde se
 * habilitan las invocaciones http
 * 
 * @author Carlos Andres Diaz
 *
 */
@Configuration
public class ConfigurationApp implements WebMvcConfigurer {

	/**
	 * Metodo que permite agregar los permisos a las solicitudes tales como origen,
	 * metodos http permitidos, parametros header permitidos etc,
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		/**
		 * MaxAge: tiempo que dura la cache por parte del cliente en mantener la
		 * peticion segura y no hacer un Preflighted_requests
		 * 
		 * AllowedHeaders: Si el cliente manda alguna data en el header, esta debe ser
		 * los permitidos por la configuracion establecida
		 * 
		 * AllowedMethods: Se especifica los metodos http permitidos
		 */
		registry.addMapping("/**")
			.allowedHeaders(HttpHeaders.CONTENT_TYPE)
			.allowedMethods("*")
			.maxAge(Long.valueOf("3600"));
	}
}
