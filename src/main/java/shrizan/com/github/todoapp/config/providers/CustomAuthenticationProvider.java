package shrizan.com.github.todoapp.config.providers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import shrizan.com.github.todoapp.config.authentication.CustomAuthentication;
import shrizan.com.github.todoapp.utils.JWTUtil;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final JWTUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var customAuthentication = (CustomAuthentication) authentication;
        var subject=jwtUtil.validateJWTToken(customAuthentication.getToken());
            customAuthentication.setAuthenticated(true);
            customAuthentication.setSubject(subject);
            return customAuthentication;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }
}
