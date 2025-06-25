package com.hu6r1s.bloom.global.jwt;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
        Arrays.stream(claims.get("auth").toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    UserDetails principal = new User(claims.getSubject(), "", authorities);
    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().decryptWith(key).build().parse(token);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
    } catch (ExpiredJwtException e) {
      throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
    } catch (UnsupportedJwtException e) {
      throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
    } catch (IllegalArgumentException e) {
      throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
    }
  }

  public String createTempToken(Authentication authentication, long expiration) {
    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity = new Date(now + expiration * 60 * 1000);
    return Jwts.builder()
        .subject(authentication.getName())
        .claim("role", authorities)
        .signWith(key)
        .expiration(validity)
        .compact();
  }
}
