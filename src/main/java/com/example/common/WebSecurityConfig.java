package com.example.common;

import static com.example.common.SecurityConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.configurationSource(corsConfigrationSource())
		.and()
		.authorizeRequests().antMatchers("/information","/showNewsList","/loginCheck", "/test", SIGNUP_URL, LOGIN_URL).permitAll()
				.anyRequest().authenticated().and().logout().and().csrf().disable()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		;
	}
	
	/**
	 * localhost:8081からアクセスした際に下記が適用される.
	 * 
	 * 
	 * 
	 * @return
	 */
	private CorsConfigurationSource corsConfigrationSource() {
		CorsConfiguration corsConfigration = new CorsConfiguration();
		corsConfigration.addAllowedHeader(corsConfigration.ALL); // Headerの制限
		corsConfigration.addAllowedMethod("POST"); // メソッドの制限
		corsConfigration.addAllowedOrigin(corsConfigration.ALL); // オリジンの制限
		List<String> exposedHeaderList = new ArrayList<>();
		exposedHeaderList.add("Authorization");
		corsConfigration.setExposedHeaders(exposedHeaderList); // レスポンスヘッダへのアクセス制限
		 UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
		corsSource.registerCorsConfiguration("/login", corsConfigration); //第1引数：適用されるパス 第2引数：制限内容
		 return corsSource;
	}
	

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}