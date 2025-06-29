package com.hu6r1s.bloom.users.dto;

import com.hu6r1s.bloom.users.entity.User;
import com.hu6r1s.bloom.users.entity.enums.Role;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthAttributes {
  private String email;
  private Map<String, Object> attributes;


  public static OAuthAttributes of(String clientName, Map<String, Object> attributes) {
    if ("Kakao".equals(clientName)) {
      return ofKakao(attributes);
    }
    return ofGoogle(attributes);
  }

  private static OAuthAttributes ofGoogle(Map<String, Object> attributes) {
    return new OAuthAttributes(
        (String) attributes.get("email"),
        attributes
    );
  }

  @SuppressWarnings("unchecked")
  private static OAuthAttributes ofKakao(Map<String, Object> attributes) {
    Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

    return new OAuthAttributes(
        (String) kakaoAccount.get("email"),
        attributes
    );
  }

  public User toEntity() {
    return User.builder()
        .email(email)
        .roles(Role.USER)
        .isActive(true)
        .build();
  }
}
