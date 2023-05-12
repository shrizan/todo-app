package shrizan.com.github.todoapp.config.managers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import shrizan.com.github.todoapp.config.providers.CustomAuthenticationProvider;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider provider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (provider.supports(authentication.getClass())) {
            var appAuth = provider.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(appAuth);
            return appAuth;
        }
        throw new BadCredentialsException("Auth failed");
    }
}
