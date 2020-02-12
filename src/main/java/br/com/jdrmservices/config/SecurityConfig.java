package br.com.jdrmservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/empresas/novo").hasAuthority("CADASTRAR_EMPRESA")
				.antMatchers("/secretarias/novo").hasAuthority("CADASTRAR_SECRETARIA")
				.antMatchers("/setores/novo").hasAuthority("CADASTRAR_SETOR")
				.antMatchers("/solicitantes/novo").hasAuthority("CADASTRAR_SOLICITANTE")
				.antMatchers("/tecnicos/novo").hasAuthority("CADASTRAR_TECNICO")
				.antMatchers("/servicos/novo").hasAuthority("CADASTRAR_SERVICO")
				.antMatchers("/comprasdiretas/novo").hasAuthority("CADASTRAR_COMPRA_DIRETA")
				.antMatchers("/execucaocontratos/novo").hasAuthority("CADASTRAR_EXECUCAO_CONTRATO")
				.antMatchers("/usuarios/novo").hasAuthority("CADASTRAR_USUARIO")
				
				.antMatchers("/empresas").hasAuthority("PESQUISAR_EMPRESA")
				.antMatchers("/secretarias").hasAuthority("PESQUISAR_SECRETARIA")
				.antMatchers("/setores").hasAuthority("PESQUISAR_SETOR")
				.antMatchers("/solicitantes").hasAuthority("PESQUISAR_SOLICITANTE")
				.antMatchers("/tecnicos").hasAuthority("PESQUISAR_TECNICO")
				.antMatchers("/servicos").hasAuthority("PESQUISAR_SERVICO")
				.antMatchers("/comprasdiretas/novo").hasAuthority("PESQUISAR_COMPRA_DIRETA")
				.antMatchers("/execucaocontratos/novo").hasAuthority("PESQUISAR_EXECUCAO_CONTRATO")
				.antMatchers("/usuarios").hasAuthority("PESQUISAR_USUARIO")
				
				.antMatchers("/relatorios").hasAuthority("EMITIR_RELATORIOS")
				
				.antMatchers("/").hasAuthority("DASHBOARD")
				.antMatchers("/admin").hasAuthority("DASHBOARD")
				.antMatchers("/dashboard").hasAuthority("DASHBOARD")
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
			.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
				.exceptionHandling().accessDeniedPage("/403")
			.and()
				//.csrf().disable()
				.sessionManagement()
					.maximumSessions(500).expiredUrl("/login");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/css/**", "/images/**", "/js/**", "/vendor/**");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
