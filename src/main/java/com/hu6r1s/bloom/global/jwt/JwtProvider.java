package com.hu6r1s.bloom.global.jwt;

import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import com.hu6r1s.bloom.users.entity.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private final SecretKey key;
  private final long accessTokenValidityInMilliseconds;
  private final long refreshTokenValidityInMilliseconds;

  public JwtProvider(@Value("${jwt.secret}") String secretKey,
      @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidity,
      @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidity) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.accessTokenValidityInMilliseconds = accessTokenValidity * 1000;
    this.refreshTokenValidityInMilliseconds = refreshTokenValidity * 1000;
  }

  public String createAccessToken(Authentication authentication) {
    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity = new Date(now + this.accessTokenValidityInMilliseconds);
    return Jwts.builder()
        .subject(authentication.getName())
        .claim("email", ((CustomUserDetails) authentication.getPrincipal()).getEmail())
        .claim("role", authorities)
        .signWith(key)
        .expiration(validity)
        .compact();
  }

  public String createRefreshToken(Authentication authentication) {
    long now = (new Date()).getTime();
    Date validity = new Date(now + this.refreshTokenValidityInMilliseconds);

    return Jwts.builder()
        .subject(authentication.getName())
        .signWith(key)
        .expiration(validity)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parser()
        .verifyWith(key)
        .build().parseSignedClaims(token).getPayload();

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get("role").toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    CustomUserDetails principal = CustomUserDetails.builder()
        .id(claims.getSubject())
        .email(claims.get("email", String.class))
        .role(Role.valueOf(claims.get("role", String.class).replace("ROLE_", "")))
        .isActive(true)
        .build();

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
      return true;
    } catch (SecurityException | MalformedJwtException e) {
      throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect.");
    } catch (ExpiredJwtException e) {
      throw new AuthenticationCredentialsNotFoundException("Expired JWT token.");
    } catch (UnsupportedJwtException e) {
      throw new AuthenticationCredentialsNotFoundException("Unsupported JWT token.");
    } catch (IllegalArgumentException e) {
      throw new AuthenticationCredentialsNotFoundException("JWT token compact of handler are invalid.");
    }
  }

  public String createTempToken(Authentication authentication, long expiration) {
    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity = new Date(now + (expiration * 1000));
    return Jwts.builder()
        .subject(authentication.getName())
        .claim("role", authorities)
        .signWith(key)
        .expiration(validity)
        .compact();
  }
}
