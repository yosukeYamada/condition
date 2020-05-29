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

	@Autowired
    private EnvironmentsConfiguration envConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.configurationSource(corsConfigrationSourceForLogin())
		.and()
		.authorizeRequests().antMatchers("/encode","/registerUser","/information","/showNewsList","/loginCheck","/getDepList", "/test", SIGNUP_URL, LOGIN_URL).permitAll()
		.antMatchers("/registerDailyPost","/registerLimit","/editDailyPost/edit").hasRole("USER")
		.antMatchers("/showEmployeeList","/news","/deleteNews","/editDeps/deleteDep","/editDeps/changeDepName","/editDeps/addNewDep","/changeAuthority","/editDeps/exclusiveProcessing","/editDeps/checkIsEmployeeBelong","/information/insert","/status","/getAggregateByDay","/getAggregateByMonth","/updateUser").hasRole("ADMIN")
		
		.anyRequest().authenticated().and().logout().and().csrf().disable()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	/**
	 * localhost:8081からアクセスした際に下記が適用される.
	 * 
	 * 
	 * 
	 * @return
	 */
	private CorsConfigurationSource corsConfigrationSourceForLogin() {
		CorsConfiguration corsConfigration = new CorsConfiguration();
		corsConfigration.addAllowedHeader(corsConfigration.ALL); // Headerの制限
		corsConfigration.addAllowedMethod(corsConfigration.ALL); // メソッドの制限
		corsConfigration.addAllowedOrigin(envConfig.getOriginUrl()); // オリジンの制限
		corsConfigration.addAllowedOrigin(envConfig.getOriginUrl()+"/**");
		List<String> exposedHeaderList = new ArrayList<>();
		exposedHeaderList.add("Authorization");
		corsConfigration.setExposedHeaders(exposedHeaderList); // レスポンスヘッダへのアクセス制限
		corsConfigration.getAllowedHeaders();
		 UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
		corsSource.registerCorsConfiguration("/**", corsConfigration); //第1引数：適用されるパス 第2引数：制限内容
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