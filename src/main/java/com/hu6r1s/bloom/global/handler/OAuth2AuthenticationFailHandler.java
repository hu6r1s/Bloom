package com.hu6r1s.bloom.global.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2AuthenticationFailHandler implements AuthenticationFailureHandler {

  @Value("${front-end.uri}")
  private String FRONT_END_URI;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    String errorMessage = URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8);

    String redirectUrl = FRONT_END_URI + "/login/failure?error=" + errorMessage;
    response.sendRedirect(redirectUrl);
  }
}
