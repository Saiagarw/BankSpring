package com.sapient.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.sapient.app.security.JwtAuthenticationEntryPoint;
import com.sapient.app.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
	
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "swagger-ui/**",

            "/auth/**",
    };
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    	
    	http.csrf(csrf->csrf.disable())
//    			.cors(cors->cors.disable())
    			.authorizeHttpRequests(auth->auth.requestMatchers("/customer/**").authenticated()
    					.requestMatchers("/auth/login").permitAll().requestMatchers(AUTH_WHITELIST).permitAll().requestMatchers("/auth/signup").permitAll().anyRequest().authenticated())
    			.exceptionHandling(ex->ex.authenticationEntryPoint(point))
    			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	
    	http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    }
    
    public DaoAuthenticationProvider doDaoAuthenticationProvider(){
    	
    	DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    	
    	return daoAuthenticationProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
