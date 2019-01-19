package pl.dmt.jwt;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.Objects;

public class Main {

    private static String secret = "s3cr3t";

    public static void main(String[] args) {
        String id = "1";
        String issuer = "qqq";
        String subject = "wwwwww";
        long ttlMillis = 100000L;

        String enc = createJWT(id, issuer, subject,ttlMillis);
        System.out.println(enc);

        parseJWT(enc);
    }

    //Sample method to construct a JWT
    private static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        MyKey apiKey = new MyKey(secret);

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    //Sample method to validate and read the JWT
    private static void parseJWT(String jwt) {

        MyKey apiKey = new MyKey(secret);

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }

    private static class MyKey {

        private String secret;

        public MyKey() {
        }

        public MyKey(String secret) {
            this.secret = secret;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyKey key = (MyKey) o;
            return Objects.equals(secret, key.secret);
        }

        @Override
        public int hashCode() {

            return Objects.hash(secret);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("secret", secret)
                    .toString();
        }
    }

}
