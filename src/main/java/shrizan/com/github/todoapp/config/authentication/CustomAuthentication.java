package shrizan.com.github.todoapp.config.authentication;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import shrizan.com.github.todoapp.domain.user.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Data
public class CustomAuthentication implements Authentication {
    private boolean authenticated;
    private User user;
    private String token;

    private String subject;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return user.getId();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return subject;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return subject;
    }
}
