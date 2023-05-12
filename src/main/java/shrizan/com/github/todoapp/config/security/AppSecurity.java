package shrizan.com.github.todoapp.config.security;

import jakarta.servlet.FilterChain;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shrizan.com.github.todoapp.config.filter.JwtTokenFilter;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Configuration
@RequiredArgsConstructor
public class AppSecurity {
    @Value("${todo.app.allowed-endpoint}")
    private String allowedEndpoints;
    private final JwtTokenFilter filter;

    @SneakyThrows
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity.csrf().disable();
        httpSecurity.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(allowedEndpoints.split(",")).permitAll()
                            .anyRequest()
                            .authenticated();
                });
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
