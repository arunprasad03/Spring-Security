package com.example.security;

import com.example.enums.ApplicationUserRole;
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

import static com.example.enums.ApplicationUserRole.ADMIN;
import static com.example.enums.ApplicationUserRole.STUDENT;

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
        UserDetails admin= User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(ADMIN.name())
                .build();

        UserDetails user= User.builder()
                .username("abc")
                .password(passwordEncoder.encode("abc"))
                .roles(STUDENT.name())
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }
}
