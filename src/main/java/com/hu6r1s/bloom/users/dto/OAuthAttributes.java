package com.hu6r1s.bloom.users.dto;

import com.hu6r1s.bloom.users.entity.enums.Role;
import com.hu6r1s.bloom.users.entity.User;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthAttributes {
  private String nickname;
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
        (String) attributes.get("name"),
        (String) attributes.get("email"),
        attributes
    );
  }

  private static OAuthAttributes ofKakao(Map<String, Object> attributes) {
    Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

    return new OAuthAttributes(
        (String) profile.get("nickname"),
        (String) kakaoAccount.get("email"),
        attributes
    );
  }

  public User toEntity() {
    return new User(nickname, email, Role.USER);
  }
}
