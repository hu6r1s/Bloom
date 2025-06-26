package com.hu6r1s.bloom.users.controller;

import com.hu6r1s.bloom.global.util.CookieUtil;
import com.hu6r1s.bloom.users.dto.request.UserSignupRequestDto;
import com.hu6r1s.bloom.users.service.UserService;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @Value("${jwt.refresh-token-validity-in-seconds}")
  private String REFRESH_TOKEN_VALIDITY;

  @PutMapping("/signup")
  public ResponseEntity<Void> completeUserRegistration(
      Authentication authentication,
      @RequestBody UserSignupRequestDto requestDto,
      HttpServletResponse response) {
    String userEmail = authentication.getName();
    Map<String, String> tokens = userService.completeRegistration(userEmail, requestDto);

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
