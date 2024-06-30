package springSecurity._FASpringSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import springSecurity._FASpringSecurity.Exception.CustomAuthenticationEntryPoint;
import springSecurity._FASpringSecurity.Filter.JwtFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	@Bean
	PasswordEncoder encoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable();
		http.authorizeHttpRequests((auth)->auth.requestMatchers("/admin")
				.authenticated().anyRequest().permitAll());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterAt(jwtFilter,UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
		return http.build();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(customUserDetailsService);
	}
	

}
