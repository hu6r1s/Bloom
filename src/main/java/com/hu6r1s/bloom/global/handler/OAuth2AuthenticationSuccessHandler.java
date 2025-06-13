package com.hu6r1s.bloom.global.handler;

import com.hu6r1s.bloom.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final JwtProvider jwtProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    String accessToken = jwtProvider.createAccessToken(authentication);
    String refreshToken = jwtProvider.createRefreshToken(authentication);

    String redirectUrl = UriComponentsBuilder.fromUriString("/login/success")
        .queryParam("accessToken", accessToken)
        .build().toUriString();

    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
  }
}
