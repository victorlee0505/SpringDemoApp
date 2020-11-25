package com.example.demo.Controller;

import java.util.Arrays;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${com.auth0.audience}")
    private String audience;
    @Value("${com.auth0.issuer}")
    private String issuer;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        JwtWebSecurityConfigurer
                .forRS256(audience, issuer)
                .configure(http)
                .authorizeRequests()

                // Note: If passing an Authorization header, Spring Security will validate it even with permitAll()
                // You can ignore security filters if this is an issue for you, as discussed here:
                // https://stackoverflow.com/questions/36296869/spring-security-permitall-still-considering-token-passed-in-authorization-header
                .antMatchers("/api/greeting").permitAll()
                .antMatchers("/api/greeting/get").authenticated();
                // .antMatchers(HttpMethod.GET, "/api/private").authenticated()
                // .antMatchers(HttpMethod.GET, "/api/private-scoped").hasAuthority("read:messages");
    }

}
