package com.mobiversa.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	public void onStartup(ServletContext container) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		container.addListener(new ContextLoaderListener(rootContext));
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
				new DispatcherServlet(new GenericWebApplicationContext()));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/resources/**", "/sms/send/otp", "/sms/validate/otp", "/signup", "/ForgotPassword",
//						"/resetPassword/request/otp", "/resetPassword/update", "/user/otp/validate", "/about",
//						"/CallBack/saveCallBackRequest", "/internal/signup")
//				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
//				.loginProcessingUrl("/authenticateTheUser").successForwardUrl("/Home").permitAll().and().logout()
//				.logoutSuccessUrl("/login");
//	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/resources/**", "/sms/send/otp", "/sms/validate/otp", "/signup", "/ForgotPassword",
//						"/resetPassword/request/otp", "/resetPassword/update", "/user/otp/validate", "/about",
//						"/CallBack/saveCallBackRequest", "/internal/signup")
//				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
//				.loginProcessingUrl("/authenticateTheUser").successForwardUrl("/Home").permitAll().and().logout()
//				.logoutSuccessUrl("/login").permitAll().and().csrf() 
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); 
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	        .antMatchers("/resources/**", "/sms/send/otp", "/sms/validate/otp", "/signup", "/ForgotPassword",
	                     "/resetPassword/request/otp", "/resetPassword/update", "/user/otp/validate", "/about",
	                     "/CallBack/saveCallBackRequest", "/internal/signup")
	        .permitAll()
	        .anyRequest()
	        .authenticated()
	        .and()
	        .formLogin()
	        .loginPage("/login")
	        .loginProcessingUrl("/authenticateTheUser")
	        .successForwardUrl("/Home")
	        .permitAll()
	        .and()
	        .logout()
	        .logoutUrl("/logout") // Set the logout URL
	        .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
	        .permitAll() // Allow all users to access the logout URL
	        .and()
	        .csrf()
	        .disable().headers()
            .xssProtection()
            .block(false)
            .xssProtectionEnabled(true); // Disable CSRF protection
	}

	

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}