package com.hu6r1s.bloom.global.handler;

import com.hu6r1s.bloom.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final JwtProvider jwtProvider;

  @Value("${front-end.uri}")
  private String FRONT_END_URI;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    String accessToken = jwtProvider.createAccessToken(authentication);
    String refreshToken = jwtProvider.createRefreshToken(authentication);

    // todo 리프레시 토큰은 Redis, HttpOnly Cookie 저장

    String redirectUrl = FRONT_END_URI + "/login/success";
    response.setHeader("Authorization", "Bearer " + accessToken);

    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
  }
}
