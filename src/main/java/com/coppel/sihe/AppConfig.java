package com.coppel.sihe;



import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.coppel.sihe.repository.UserRepository;
import com.coppel.sihe.util.Cifrado;
import com.coppel.sihe.util.Log;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@PropertySources({
	@PropertySource(value="file:/opt/BanCoppel/config/sihe/datos.properties", ignoreResourceNotFound=true),
	@PropertySource(value="file:C:/Jboss7/Componentes/datos.properties", ignoreResourceNotFound=true)
    })public class AppConfig {
	
	@Value("${default.folder.path}")
	private String default_path;
	
	private String passKey;
	private final UserRepository userRepository;
	
	
	@Bean
	public RestTemplate skillsRest() {
	    return new RestTemplate();
	}
	
	public void config() {
		String folder = default_path+"logs/";
		System.out.println(folder);
		
		 File directorio = new File(folder);
		 Log.setFolder_log(folder);
		 if (!directorio.exists()) {
			 if (directorio.mkdirs()) {
				 System.out.println("Directorio creado");
			 } else {
				 System.out.println("Error al crear directorio");
			 }
        }
		passKey = "S1H3C0pp3L";
		Cifrado.setClave(passKey);
		System.out.println("Clave configurada.....");
	}

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.consultaUsuarioByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}
