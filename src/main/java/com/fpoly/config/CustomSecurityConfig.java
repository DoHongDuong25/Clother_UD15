package com.fpoly.config;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fpoly.entity.NguoiDung;
import com.fpoly.repository.NguoiDungRepository;
import com.fpoly.service.impl.NguoiDungDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private NguoiDungRepository nguoiDungRepository;
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncorder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(new NguoiDungDetailsService());
		authProvider.setPasswordEncoder(passwordEncorder());
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(email -> {
			try {
				NguoiDung nguoiDung = nguoiDungRepository.findByEmailAndTrangThaiAndDaXoa(email);
				String password = passwordEncorder().encode(nguoiDung.getMatKhau());
				String[] roles = nguoiDung.getListNguoiDungVaiTro().stream()
						.map(er -> er.getVaiTro().getCode())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(email).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(email + "not found!");
			}
		});
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(
				"/customer/css/**","/customer/fonts/**","/customer/js/**","/customer/images/**","/customer/login/**",
				
				
				
				
				"/admin/css/**","/admin/images/**","/admin/img/**","/admin/imgLibr/**",
				"/admin/js/**","/admin/js/khachhang/**","/admin/scss/**","/admin/vendor/**","/admin/vendor test/**",
		
			    "/security/**"
				)
		.permitAll()
		.antMatchers("/admin/**").hasAnyRole("ADMIN","STAFF")
		.antMatchers("/customer/**").hasAnyRole("ADMIN","STAFF","CUSTOMER");
		
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/security/login/success?alert=success")
			.failureUrl("/security/login/error?alert=danger");
		http.rememberMe()
		.tokenValiditySeconds(86400);
	
		http.exceptionHandling()
			.accessDeniedPage("/security/unauthoried?alert=danger");
		
		http.logout()
			.logoutUrl("/security/logoff")
			.logoutSuccessUrl("/security/logoff/success?alert=success");
		http.csrf().disable();
	}
	
	
	
}
