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

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
class JwtKeyStore {
    private final String ACCESS_PUBLIC_KEY = "public_access";

    private Map<String, Key> keys;

    public JwtKeyStore() {
        keys = new HashMap<>();
    }

    @PostConstruct
    private void initializeKeys() throws InvalidKeySpecException,
            NullPointerException, IOException, NoSuchAlgorithmException {
        KeyFactory rsaKeyFactory = KeyFactory.getInstance("RSA");
        
        //rewrite
        String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvIzjXUQohb+wHwBUZOGOfsUkKF4mRsU1VAIdzcp1iyef0ooirNYe8mvTyRMQFevsBfsxidMIe7thcZ39As2fGjI1MgIGQMy2ItfSC/6so8NAVCcXK4oEHgPGfMkbeFJbF+Qfd7SXknRQlKYP1fn+saJt3QT6TMFKJx64+NAb2jU+dvqnSn/KIBRdsKec3VkiBJywvILHQF0P1GpJK/GntxJH4xb/iKeFrlsYlaMtQCeEVHcmX66lqn5KSPq76z/0GMDhFw6Bz2nQiotnYBMnlOpxGvTgyUX7O0WRHhyeufv2vygn6NshCm6HduhYsYmcJm62qh1xivy5j9d884wonwIDAQAB";
        
        var publicBytes = Base64.getDecoder().decode(key);
        var pubKeySpec = new X509EncodedKeySpec(publicBytes);
        var pubKey = rsaKeyFactory.generatePublic(pubKeySpec);
        keys.put(ACCESS_PUBLIC_KEY, pubKey);
    }

    public PublicKey getAccessPublicKey() {
        return (PublicKey)keys.get(ACCESS_PUBLIC_KEY);
    }
}