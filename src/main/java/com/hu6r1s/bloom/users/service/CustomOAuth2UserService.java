package com.hu6r1s.bloom.users.service;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest request) {
    OAuth2User oAuth2User = super.loadUser(request);

    String oauthClientName = request.getClientRegistration().getClientName();
    Map<String, Object> attributes = oAuth2User.getAttributes();

    OAuthAttributes oAuthAttributes = OAuthAttributes.of(oauthClientName, attributes);

    Optional<User> user = userRepository
  }
}
