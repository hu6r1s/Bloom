package com.hu6r1s.bloom.users.entity;

import com.hu6r1s.bloom.users.dto.request.ProfileRequestDto;
import com.hu6r1s.bloom.users.entity.enums.BodyType;
import com.hu6r1s.bloom.users.entity.enums.ChildPlan;
import com.hu6r1s.bloom.users.entity.enums.DrinkingFrequency;
import com.hu6r1s.bloom.users.entity.enums.Religion;
import com.hu6r1s.bloom.users.entity.enums.Smoking;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "profiles")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Profile {

  @Id
  private String id;

  @Field("user_id")
  @Indexed(unique = true)
  private String userId;

  @Field("height")
  private double height;

  @Field("body_type")
  private BodyType bodyType;

  @Field("company")
  private String company;

  @Field("education")
  private String education;

  @Field("annual_income")
  private Long annualIncome;

  @Field("smoking")
  private Smoking smoking;

  @Field("drinking_frequency")
  private DrinkingFrequency drinkingFrequency;

  @Field("mbti")
  private Mbti mbti;

  @Field("political_tendency")
  private PoliticalTendency politicalTendency;

  @Field("religion")
  private Religion religion;

  @Field("child_plan")
  private ChildPlan childPlan;

  @Field("marriage_thoughts")
  private String marriageThoughts;

  @Field("personal_keywords")
  private String personalKeywords;

  @Field("core_values")
  private String coreValues;

  @Field("life_priorities")
  private String lifePriorities;

  @Field("hobbies_interests")
  private String hobbiesInterests;

  @Field("weekend_style")
  private String weekendStyle;

  @Field("date_style")
  private String dateStyle;

  @Field("communication_style")
  private String communicationStyle;

  @Field("relationship_view")
  private String relationshipView;

  @Field("self_introduction")
  private String selfIntroduction;

  @CreatedDate
  @Field("created_at")
  private String createdAt;

  @LastModifiedDate
  @Field("updated_at")
  private String updatedAt;

  public void update(ProfileRequestDto requestDto) {
    Optional.ofNullable(requestDto.getHeight()).ifPresent(value -> this.height = value);
    Optional.ofNullable(requestDto.getBodyType()).ifPresent(value -> this.bodyType = value);
    Optional.ofNullable(requestDto.getCompany()).ifPresent(value -> this.company = value);
    Optional.ofNullable(requestDto.getEducation()).ifPresent(value -> this.education = value);
    Optional.ofNullable(requestDto.getAnnualIncome()).ifPresent(value -> this.annualIncome = value);
    Optional.ofNullable(requestDto.getSmoking()).ifPresent(value -> this.smoking = value);
    Optional.ofNullable(requestDto.getDrinkingFrequency()).ifPresent(value -> this.drinkingFrequency = value);
    Optional.ofNullable(requestDto.getMbti()).ifPresent(value -> this.mbti = value);
    Optional.ofNullable(requestDto.getPoliticalTendency()).ifPresent(value -> this.politicalTendency = value);
    Optional.ofNullable(requestDto.getReligion()).ifPresent(value -> this.religion = value);
    Optional.ofNullable(requestDto.getChildPlan()).ifPresent(value -> this.childPlan = value);
    Optional.ofNullable(requestDto.getMarriageThoughts()).ifPresent(value -> this.marriageThoughts = value);
    Optional.ofNullable(requestDto.getPersonalKeywords()).ifPresent(value -> this.personalKeywords = value);
    Optional.ofNullable(requestDto.getCoreValues()).ifPresent(value -> this.coreValues = value);
    Optional.ofNullable(requestDto.getLifePriorities()).ifPresent(value -> this.lifePriorities = value);
    Optional.ofNullable(requestDto.getHobbiesInterests()).ifPresent(value -> this.hobbiesInterests = value);
    Optional.ofNullable(requestDto.getWeekendStyle()).ifPresent(value -> this.weekendStyle = value);
    Optional.ofNullable(requestDto.getDateStyle()).ifPresent(value -> this.dateStyle = value);
    Optional.ofNullable(requestDto.getCommunicationStyle()).ifPresent(value -> this.communicationStyle = value);
    Optional.ofNullable(requestDto.getRelationshipView()).ifPresent(value -> this.relationshipView = value);
    Optional.ofNullable(requestDto.getSelfIntroduction()).ifPresent(value -> this.selfIntroduction = value);
  }
}
