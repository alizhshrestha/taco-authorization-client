package com.example.tacoauthorizationclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests(
//                        authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .authorizeHttpRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/taco-admin-client-oidc"))
                .oauth2Client(Customizer.withDefaults());

        return http.build();
    }
}
