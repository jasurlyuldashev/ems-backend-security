package net.java.emsbackendsecurity.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import net.java.emsbackendsecurity.service.JWTService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();

//        Key key = Keys.hmacShaKeyFor(getSignKey().getEncoded());
//
//        return Jwts.builder()
//                .claim("username", userDetails.getUsername())
//                .issuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(key)
//                .compact();
    }

    public String generateRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();

//        Key key = Keys.hmacShaKeyFor(getSignKey().getEncoded());
//
//        return Jwts.builder()
//                .claim("username", userDetails.getUsername())
//                .issuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(key)
//                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }


    private Key getSignKey() {
        String secretKey = "tN/C6WG4wokb9INvZzMTnvjiQYyg4Tunj5BTfl7Ye3s=";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token,UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }


}
