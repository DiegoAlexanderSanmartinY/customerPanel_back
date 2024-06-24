package com.example.customers1.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.customers1.entities.User;

public class JwtUtil {

    private static final String SECRET_KEY = "Diego";

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public static String generateToken(User user) {
        
         return JWT.create()
            .withIssuer("Diego")
            .withExpiresAt(getExpiresDate())
            .withClaim("userId", user.getId())
            .withIssuedAt(new Date())
            .sign(algorithm);  
    }

    private static Date getExpiresDate() {
        return new Date(System.currentTimeMillis()
                    + (1000L * 60 * 60 * 24 * 14));
    }

    public static String getUserIdByToken(String jwtToken) {
        
        JWTVerifier verifier = JWT.require(algorithm)
                                .withIssuer("Diego")
                                .build();
        
        DecodedJWT decoded = verifier.verify(jwtToken);
      
        String userId = decoded.getClaim("userId").toString();

   

        return userId;

    }

}
