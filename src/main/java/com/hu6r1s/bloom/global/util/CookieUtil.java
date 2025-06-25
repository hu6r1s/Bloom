package com.hu6r1s.bloom.global.util;

import org.springframework.http.ResponseCookie;

public class CookieUtil {

  public static ResponseCookie createRefreshTokenCookie(String refreshToken, long maxAgeInSeconds) {
    return ResponseCookie.from("refresh_token", refreshToken)
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(maxAgeInSeconds)
        .sameSite("Lax")
        .build();
  }

  public static ResponseCookie createExpiredCookie() {
    return ResponseCookie.from("refresh_token", "")
        .maxAge(0)
        .path("/")
        .build();
  }
}
