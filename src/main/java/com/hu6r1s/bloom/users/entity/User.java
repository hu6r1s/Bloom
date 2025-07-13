package com.hu6r1s.bloom.users.entity;

import com.hu6r1s.bloom.users.entity.enums.Gender;
import com.hu6r1s.bloom.users.entity.enums.Role;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
@Getter
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {

  @Id
  private String id;

  @Field("email")
  private String email;

  @Field("nickname")
  private String nickname;

  @Field("roles")
  private Role roles;

  @Field("age")
  private Integer age;

  @Field("gender")
  private Gender gender;

  @Field("residence_region")
  private String residenceRegion;

  @Field("occupation")
  private String occupation;

  @Field("phone_number")
  private String phoneNumber;

  @Field("phone_verified")
  private boolean phoneVerified = false;

  @Field("is_active")
  private boolean isActive;

  @CreatedDate
  @Field("created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Field("updated_at")
  private LocalDateTime updatedAt;

  @Field("deleted_at")
  private LocalDateTime deletedAt;

  public void completeUserRegistration(String nickname, Integer age, Gender gender, String residenceRegion, String occupation) {
    this.nickname = nickname;
    this.age = age;
    this.gender = gender;
    this.residenceRegion = residenceRegion;
    this.occupation = occupation;
  }

  public boolean isRegistrationComplete() {
    return nickname != null && !nickname.isBlank()
        && age != null
        && gender != null
        && residenceRegion != null && !residenceRegion.isBlank()
        && occupation != null && !occupation.isBlank();
  }
}
