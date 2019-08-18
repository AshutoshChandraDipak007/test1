package com.telusko.secureapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userServiceDetails;  // Use for getting the user records
	
	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(); //provider is talking to service and service will talk to dao.
		provider.setUserDetailsService(userServiceDetails);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());  //plane text password
		//provider.setPasswordEncoder(new BCryptPasswordEncoder());		// BCrypt password
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {  // Customize login,logout,success page setting
		http
		.csrf().disable()		//disabling csrf
		.authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()				//enable login form action name 
		.loginPage("/login").permitAll()   // allow login page accessible by all user
		.and()
		.logout().invalidateHttpSession(true) //after logout session deleting
		.clearAuthentication(true)				//clear authentication
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //
		.logoutSuccessUrl("/logout-success").permitAll();		//after logout call logout-success
		
	} 
	

}
