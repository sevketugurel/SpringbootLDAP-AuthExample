package com.example.springboot_ldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DefaultSpringSecurityContextSource contextSource;

    @Autowired
    public SecurityConfig(DefaultSpringSecurityContextSource contextSource) {
        this.contextSource = contextSource;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
            .userDnPatterns("uid={0},ou=people")
            .contextSource(contextSource)
            .ldapAuthoritiesPopulator((userData, username) -> 
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/error").permitAll()
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .permitAll())
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .permitAll())
            .build();
    }
}