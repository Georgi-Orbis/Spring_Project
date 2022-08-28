package com.orbisexample.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> detailsService = auth.userDetailsService(userDetailsService);
        detailsService.passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/logout").hasAnyRole("ADMIN", "USER")
                .antMatchers("/**").hasRole("ADMIN")
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}








  // private final DataSource dataSource;

  // public SecurityConfiguration(DataSource dataSource) {
  //     this.dataSource = dataSource;
  // }


  //  @Bean
  //  public PasswordEncoder getPasswordEncoder(){
  //      return new BCryptPasswordEncoder();
  //  }







  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //     auth.jdbcAuthentication().dataSource(dataSource);
  // }

 //  @Override
 //  protected void configure(HttpSecurity http) throws Exception{
 //      http.csrf().disable()
 //              .authorizeRequests()
 //              .antMatchers("/").permitAll()
 //              .antMatchers("/users").hasAnyRole("ADMIN", "USER")
 //              .antMatchers("/**").hasRole("ADMIN")

 //              .and().httpBasic();
 //  }



