package com.orbisexample.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {




    private final UserDetailsService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService myUserDetailsService, UserDetailsService userDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> detailsService =
                auth.userDetailsService(userDetailsService);
        detailsService.passwordEncoder(new BCryptPasswordEncoder());


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate").permitAll()
                .antMatchers("/", "/swagger-ui/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/v3/api-docs").permitAll()
                .antMatchers("/users/**", "/user/**").hasAnyRole("ADMIN", "USER")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().and().httpBasic();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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



