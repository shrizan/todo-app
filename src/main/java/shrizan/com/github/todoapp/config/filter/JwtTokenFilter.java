package shrizan.com.github.todoapp.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import shrizan.com.github.todoapp.config.authentication.CustomAuthentication;
import shrizan.com.github.todoapp.config.managers.CustomAuthenticationManager;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager manager;

    @Value("${todo.app.allowed-endpoint}")
    private String allowedEndpoints;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader("jwt-token");
        var authentication = new CustomAuthentication();
        authentication.setToken(token);
        manager.authenticate(authentication);
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(allowedEndpoints.split(",")).anyMatch(endpoint -> endpoint.equals(request.getServletPath()));
    }
}
