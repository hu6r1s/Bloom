package com.hu6r1s.bloom.auth.controller;

import com.hu6r1s.bloom.auth.service.AuthService;
import com.hu6r1s.bloom.global.util.CookieUtil;
import com.hu6r1s.bloom.users.dto.request.UserSignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @Value("${jwt.refresh-token-validity-in-seconds}")
  private String REFRESH_TOKEN_VALIDITY;

  @PutMapping("/signup")
  public ResponseEntity<Void> completeUserRegistration(
      Authentication authentication,
      @RequestBody UserSignupRequestDto requestDto,
      HttpServletResponse response) {
    String userEmail = authentication.getName();
    Map<String, String> tokens = authService.completeRegistration(userEmail, requestDto);

    String accessToken = tokens.get("accessToken");
    String refreshToken = tokens.get("refreshToken");

    ResponseCookie refreshTokenCookie = CookieUtil.createRefreshTokenCookie(refreshToken,
        Long.parseLong(REFRESH_TOKEN_VALIDITY));
    response.addHeader("Set-Cookie", refreshTokenCookie.toString());

    return ResponseEntity.ok()
        .header("Authorization", "Bearer " + accessToken)
        .build();
  }
}
