package com.hu6r1s.bloom.users.dto.response;

import com.hu6r1s.bloom.users.entity.enums.Gender;
import com.hu6r1s.bloom.users.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserInfoResponseDto {

  private String id;
  private String nickname;
  private Role roles;
  private Integer age;
  private Gender gender;
  private String residenceRegion;
  private String occupation;
  private boolean phoneVerified;
}
