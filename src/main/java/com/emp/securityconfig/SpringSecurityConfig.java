package com.emp.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.emp.impl.EmployeeServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().cors().and().authorizeHttpRequests()
				.antMatchers("/employee/register","/employee/updateEmployee/{Id}","/employee/getEmployee/{Id}","/employee/deleteEmployee/{Id}", "/employee/login","/employee/getAllEmployee","/employee/logout").permitAll().and().authorizeHttpRequests()
				.antMatchers("/employee/**").authenticated().and()
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		// TODO Auto-generated method stub
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
