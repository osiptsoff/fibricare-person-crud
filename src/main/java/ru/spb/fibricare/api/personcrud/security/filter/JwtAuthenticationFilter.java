package ru.spb.fibricare.api.personcrud.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ru.spb.fibricare.api.personcrud.security.jwt.JwtUtility;

/**
 * <p>Filter in filter chain responsible for jwt-based authorization.</p>
 * 
 * <p>Checks if request has jwt access token in header and if it has, performs validation.</p>
    * @author Nikita Osiptsov
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtility jwtUtility;
    private final ObjectMapper jsonObjectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.substring("Bearer ".length());

        UserDetails userDetails;
        try {
            userDetails = jwtUtility.parseAndValidateAccessToken(token);
        } catch(JwtException e) {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter()
                .write(jsonObjectMapper
                    .writeValueAsString(getMessage(HttpStatus.UNAUTHORIZED, e.getMessage())));
            return;
        }        

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(),
            null,
            userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private Map<String, String> getMessage(HttpStatus status, String message) {
        Map<String, String> errorMessage = new HashMap<>();

        errorMessage.put("status", Integer.toString(status.value()));
        errorMessage.put("error", status.getReasonPhrase());
        errorMessage.put("message", message);

        return errorMessage;
    }
}
