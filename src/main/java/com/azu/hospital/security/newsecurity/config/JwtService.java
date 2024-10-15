package com.azu.hospital.security.newsecurity.config;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("jwt")
public class JwtService {


    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    @Value("${app.jwt.refresh-expiration}")
    private long refreshExpiration;

//  @Value("${app.jwt.privatekey}")
//  private PrivateKey privateKey;
//
//  @Value("${app.jwt.publickey}")
//  private PublicKey publicKey;

//  private final ConversionService conversionService;

//  public JwtService( ConversionService conversionService) {
//    this.conversionService = conversionService;
//  }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, Long userId, Set<Role> roles) {
        return generateToken(new HashMap<>(), userDetails, userId, roles.stream().map(Role::getName).collect(Collectors.toSet()));
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            Long userId,
            Set<String> roles
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration, userId, roles);
    }

    public String generateRefreshToken(
            UserDetails userDetails,
            Long userId,
            Set<Role> roles
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration, userId, roles.stream().map(Role::getName).collect(Collectors.toSet()));
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration,
            Long userId,
            Set<String> roles

    ) {
        extraClaims.put("userId", userId);
        extraClaims.put("roles", roles);
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }
    //, SignatureAlgorithm.RS512)

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims extractAllClaimsFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //  private Key getSignInKey() {
//    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//    return Keys.hmacShaKeyFor(keyBytes);
//  }
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
//}

//  private Key getSignInKey() {
//    return conversionService.convert(privateKey, PrivateKey.class);
//  }
}
