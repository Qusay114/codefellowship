package codefellowship.security;

import codefellowship.infrastructure.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImp userServiceImp ;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImp).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/" , "/signup").permitAll()
                .antMatchers("/users/**").hasAuthority("USER")
                .anyRequest().hasAnyAuthority("ADMIN" , "USER")
                .and()
                .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/" , true).failureUrl("/login/error")
                .and()
                .logout().permitAll().logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

        http.cors().disable().csrf().disable();
    }
}
