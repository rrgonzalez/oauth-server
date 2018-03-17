package cl.newit.oauth2baas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class OauthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		System.out.println("HTTP: " + http.toString());
		
		http.requestMatchers()
        .antMatchers("/login", "/authorize", "/token")
        .and()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll();
//        .and()
 //       .exceptionHandling()
 //       	.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager)
          .inMemoryAuthentication()
          .withUser("john").password("123").roles("USER");
    }
	
}
