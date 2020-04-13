package br.com.jdrmservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
@EnableAutoConfiguration
public class OrdemComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdemComprasApplication.class, args);
	}
	
	@Bean
	public FixedLocaleResolver fixedLocalResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
