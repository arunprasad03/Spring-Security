package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
 @EnableWebSecurity
public class AppicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //For mapping passwordencoder
    private PasswordEncoder passwordEncoder;

    public AppicationSecurityConfiguration(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder=passwordEncoder;

    }

    //Basic Authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user= User.builder()
                .username("abc")
                .password(passwordEncoder.encode("abc"))
                .roles("student")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
