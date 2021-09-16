package com.product.svc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${admin.user.name}")
	private String adminUserName;
	
	@Value("${admin.user.password}")
	private String adminPassword;
	
	@Value("${admin.user.role}")
	private String adminRole;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(adminUserName).password(adminPassword).roles(adminRole);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/review/**").permitAll()
				.antMatchers(HttpMethod.POST, "/review").hasRole(adminRole)
				.antMatchers(HttpMethod.PUT, "/review/**").hasRole(adminRole)
				.antMatchers(HttpMethod.PATCH, "/review/**").hasRole(adminRole)
				.antMatchers(HttpMethod.DELETE, "/review/**").hasRole(adminRole)
				.antMatchers("/swagger-resources", "/swagger-ui.html").permitAll()
				.and().csrf().disable().formLogin().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
