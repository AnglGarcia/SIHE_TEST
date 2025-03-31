package com.coppel.sihe.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.coppel.sihe.config.filter.JwtFilterRequest;
import com.coppel.sihe.service.EmpleadoDetailsServiceImpl;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private EmpleadoDetailsServiceImpl empServDetaImp;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(empServDetaImp);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and()
    	.csrf().disable()
    	.authorizeRequests()
    	.antMatchers("/**/recuperaConstrasenia"
    				,"/**/authenticate"
    				,"/**/solicitudessave"
    				,"/**/resetConstrasenia"
    				,"/swagger-resources/**"
    				,"/v2/api-docs"
    				,"/configuration/ui"
    				,"/swagger-resources"
    				,"/configuration/security"
    				,"/swagger-ui.html"
    				,"/webjars/**")
    	.permitAll()
        .anyRequest().authenticated().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       	 http.addFilterBefore( jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    
    
    
   
    
}