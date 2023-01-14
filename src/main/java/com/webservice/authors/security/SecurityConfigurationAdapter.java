package com.webservice.authors.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    //устанавливает логин и пароль вместо сгенерированных
   // @Override
  //  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //      auth.inMemoryAuthentication().withUser("myuser").password("{noop}mypassword").roles("USER");
  //  }

    //убирает аутентификацию для всех API
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("*/**").permitAll();
    }

    //убирает аутентификацию для определенного API
   // @Override
    //protected void configure(HttpSecurity http) throws Exception {
  //      http.httpBasic().disable();
  //      http.authorizeRequests().antMatchers("/author/all").permitAll().anyRequest().authenticated();
  //  }

}
