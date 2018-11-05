package pl.piomin.services.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@Order(-10)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	private UserDetailsService customUserDetailsService;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.sessionManagement().
////		http.authorizeRequests().antMatchers("/**").permitAll();
////		http.authorizeRequests().anyRequest().authenticated();
////		and().formLogin().loginPage("/login").permitAll().and().httpBasic().disable();
//		http.csrf().disable().requestMatchers().antMatchers("/login", "/uaa/oauth/**","/oauth/**").and().authorizeRequests().anyRequest()
//		.authenticated().and().formLogin().permitAll();
//	}
//
//	 @Override
//	 public void configure(AuthenticationManagerBuilder auth) throws Exception
//	 {
//		 auth.parentAuthenticationManager(authenticationManager).userDetailsService(customUserDetailsService);
//	 }
//
//}

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService customUserDetailsService;

	/* to setup HttpSecurity,like which urls are authenticated */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().requestMatchers().antMatchers("/login", "/uaa/oauth/**").and().authorizeRequests().anyRequest()
				.authenticated().and().formLogin().permitAll();
		 

	}

	/* to make AuthenticationManager use database */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManager).userDetailsService(customUserDetailsService);
	}

	/* to define access ignore URLs */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/common/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources",
				"/configuration/security", "/swagger-ui.html", "/webjars/**");
	}
}

