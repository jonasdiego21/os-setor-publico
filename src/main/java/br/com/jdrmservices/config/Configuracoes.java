package br.com.jdrmservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import br.com.jdrmservices.storage.FileStorage;
import br.com.jdrmservices.storage.local.FileStorageLocal;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class Configuracoes {
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
	
	@Bean
	public FileStorage fileStorage() {
		return new FileStorageLocal();
	}
}