package ru.spb.fibricare.api.personcrud.security.jwt;

import java.security.Key;
import java.security.PublicKey;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import ru.spb.fibricare.api.personcrud.security.model.GrantedAuthorityImpl;
import ru.spb.fibricare.api.personcrud.security.model.UserDetailsImpl;

@Component
@RequiredArgsConstructor
public class JwtUtility {
    private final JwtKeyStore keyStore;

    public Key getAccessPublicKey() {
        return keyStore.getAccessPublicKey();
    }

    public UserDetails parseAndValidateAccessToken(String refreshToken)
            throws ExpiredJwtException, JwtException {
        return generateUserDetails(refreshToken, keyStore.getAccessPublicKey());
    }

    private UserDetails generateUserDetails(String token, PublicKey key)
            throws ExpiredJwtException, JwtException {
        Claims claims = parseToken(token, key);

        areClaimsValid(claims);

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setId(Long.parseLong(claims.get("userId").toString()));
        userDetails.setLogin(claims.getSubject());
        userDetails.setAuthorities(claims
            .getAudience()
            .stream()
            .map(a -> new GrantedAuthorityImpl(a))
            .collect(Collectors.toSet())
        );

        return userDetails;
    }

    private Claims parseToken(String token, PublicKey key) throws ExpiredJwtException, JwtException {
        try {
            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch(ExpiredJwtException e) {
            throw e;
        } catch(JwtException | IllegalArgumentException e) {
            throw new JwtException("Failed to parse token");
        }
    }

    private boolean areClaimsValid(Claims claims) throws JwtException {
        if(claims.getIssuedAt() == null
                || claims.getExpiration() == null
                || claims.getSubject() == null
                || claims.getAudience() == null) {
            throw new JwtException("Token has invalid content");
        }

        return true;
    }
}
