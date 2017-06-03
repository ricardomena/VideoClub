package videoclub.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired   
    public CustomAuthenticationProvider authenticationProvider ;
 
   // Same authentication schema than examples before
   //@Override   protected void configure(AuthenticationManagerBuilder auth)      throws Exception {      // Database authentication provider      auth.authenticationProvider(authenticationProvider);   } 
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();
        
        http.authorizeRequests().anyRequest().authenticated();
        
        http.formLogin().loginPage("/login").usernameParameter("username")
        .passwordParameter("password").defaultSuccessUrl("/pruebas2")
        .failureUrl("/login?error").permitAll();
        
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
        
        
    
    }
    
    @Override
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.authenticationProvider(authenticationProvider);
         //auth.inMemoryAuthentication().withUser("user").password("p1").roles("USER");
         //auth.inMemoryAuthentication().withUser("root").password("p2").roles("USER", "ADMIN");
       
        
    
    }

}