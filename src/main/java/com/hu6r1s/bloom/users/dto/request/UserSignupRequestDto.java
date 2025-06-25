package com.hu6r1s.bloom.users.dto.request;

import com.hu6r1s.bloom.users.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
  private String nickname;
  private Integer age;
  private Gender gender;
  private String residenceRegion;
  private String occupation;
}
