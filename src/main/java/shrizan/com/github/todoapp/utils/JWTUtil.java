package shrizan.com.github.todoapp.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shrizan.com.github.todoapp.domain.user.User;
import shrizan.com.github.todoapp.dto.UserDTO;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author Shreejan Acharya on 5/7/2023
 * @project todo-app
 */
@Component
public class JWTUtil {
    @Value("${todo.app.jwt-secret}")
    private String secretKey;
    private final Calendar CALENDAR_INSTANCE = Calendar.getInstance();

    private Date expirationDate() {
        Date currentDate = new Date();
        CALENDAR_INSTANCE.setTime(currentDate);
        CALENDAR_INSTANCE.add(Calendar.DATE, 1);
        return CALENDAR_INSTANCE.getTime();
    }

    private Date issueDate() {
        return new Date();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJWTToken(UserDTO user) {
        return Jwts.builder().signWith(getKey())
                .setIssuer("todo-app")
                .setSubject(user.getUserName())
                .setIssuedAt(issueDate())
                .setExpiration(expirationDate())
                .setId(UUID.randomUUID().toString()).compact();
    }

    public String validateJWTToken(String jwtToken) {
            var parseClaimsJws=Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(jwtToken);
            return parseClaimsJws.getBody().getSubject();
    }
}
