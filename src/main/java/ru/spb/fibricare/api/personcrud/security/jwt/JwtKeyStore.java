package ru.spb.fibricare.api.personcrud.security.jwt;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
class KeyDto {
    private String key;
}

@Component
class JwtKeyStore {
    private final String ACCESS_PUBLIC_KEY = "public_access";

    private Map<String, Key> keys;
    @Setter
    @Value("${app.config.security.keyEndpoint}")
    private String keyEndpoint;

    public JwtKeyStore() {
        keys = new HashMap<>();
    }

    @PostConstruct
    protected void initializeKeys() throws InvalidKeySpecException,
            NullPointerException, IOException, NoSuchAlgorithmException,
            RestClientException {
        var restTemplate = new RestTemplate();
        
        String encodedKey = restTemplate
            .getForObject(keyEndpoint, KeyDto.class)
            .getKey();
        
        var rsaKeyFactory = KeyFactory.getInstance("RSA");
        var keyBytes = Base64.getDecoder().decode(encodedKey);
        var pubKeySpec = new X509EncodedKeySpec(keyBytes);
        var pubKey = rsaKeyFactory.generatePublic(pubKeySpec);

        keys.put(ACCESS_PUBLIC_KEY, pubKey);
    }

    public PublicKey getAccessPublicKey() {
        return (PublicKey)keys.get(ACCESS_PUBLIC_KEY);
    }
}