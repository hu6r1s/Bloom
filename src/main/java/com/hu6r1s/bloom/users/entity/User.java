package com.hu6r1s.bloom.users.entity;

import com.hu6r1s.bloom.users.entity.enums.Gender;
import com.hu6r1s.bloom.users.entity.enums.Role;
import java.time.LocalDateTime;
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
  private boolean isActive = true;

  @CreatedDate
  @Field("created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Field("updated_at")
  private LocalDateTime updatedAt;

  @Field("deleted_at")
  private LocalDateTime deletedAt;

  public User(String nickname, String email, Role role) {
    this.nickname = nickname;
    this.email = email;
    this.roles = role;
  }

  public void update(String nickname) {
    this.nickname = nickname;
  }
}
