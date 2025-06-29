package com.hu6r1s.bloom.global.handler;

import com.hu6r1s.bloom.global.jwt.JwtProvider;
import com.hu6r1s.bloom.global.util.CookieUtil;
import com.hu6r1s.bloom.global.util.RefreshTokenService;
import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import com.hu6r1s.bloom.users.entity.User;
import com.hu6r1s.bloom.users.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final JwtProvider jwtProvider;
  private final RefreshTokenService refreshTokenService;
  private final UserRepository userRepository;

  @Value("${front-end.uri}")
  private String FRONT_END_URI;

  @Value("${jwt.refresh-token-validity-in-seconds}")
  private String REFRESH_TOKEN_VALIDITY;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication)
      throws IOException {
    CustomUserDetails oAuth2User = (CustomUserDetails) authentication.getPrincipal();
    User user = userRepository.findById(oAuth2User.getName())
        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

    String targetUrl;
    String accessToken;
    String refreshToken;
    if (user.isRegistrationComplete()) {
      accessToken = jwtProvider.createAccessToken(authentication);
      refreshToken = jwtProvider.createRefreshToken(authentication);

      response.setHeader("Authorization", "Bearer " + accessToken);

      refreshTokenService.saveToken(
          String.format("%s::%s", user.getId(), user.getNickname()), refreshToken,
          Long.parseLong(REFRESH_TOKEN_VALIDITY));
      ResponseCookie responseCookie = CookieUtil.createRefreshTokenCookie(refreshToken,
          Long.parseLong(REFRESH_TOKEN_VALIDITY));
      response.addHeader("Set-Cookie", responseCookie.toString());

      targetUrl = "/login-success.html"; // todo 추후 변경
    } else {
      String signupToken = jwtProvider.createTempToken(authentication, 600L);
      targetUrl = UriComponentsBuilder.fromUriString("/signup.html")
          .queryParam("signupToken", signupToken)
          .build().toUriString();
    }

    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
