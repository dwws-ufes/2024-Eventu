package br.ufes.inf.eventu.app;

import br.ufes.inf.eventu.app.domain.enums.UserRole;
import br.ufes.inf.eventu.app.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService detailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(a -> a
						.requestMatchers("/tickets/**").hasRole(UserRole.ADMIN.name())
						.anyRequest()
						.authenticated())
				.httpBasic(withDefaults())
				.formLogin(withDefaults())
				.logout(withDefaults());
		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(detailsService);
		auth.setPasswordEncoder(new BCryptPasswordEncoder());
		return auth;
	}
}