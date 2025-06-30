package com.hu6r1s.bloom.users.dto.request;

import com.hu6r1s.bloom.users.entity.Mbti;
import com.hu6r1s.bloom.users.entity.PoliticalTendency;
import com.hu6r1s.bloom.users.entity.Profile;
import com.hu6r1s.bloom.users.entity.enums.BodyType;
import com.hu6r1s.bloom.users.entity.enums.ChildPlan;
import com.hu6r1s.bloom.users.entity.enums.DrinkingFrequency;
import com.hu6r1s.bloom.users.entity.enums.Religion;
import com.hu6r1s.bloom.users.entity.enums.Smoking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDto {

  private Double height;
  private BodyType bodyType;
  private String company;
  private String education;
  private Long annualIncome;
  private Smoking smoking;
  private DrinkingFrequency drinkingFrequency;
  private Mbti mbti;
  private PoliticalTendency politicalTendency;
  private Religion religion;
  private ChildPlan childPlan;
  private String marriageThoughts;
  private String personalKeywords;
  private String coreValues;
  private String lifePriorities;
  private String hobbiesInterests;
  private String weekendStyle;
  private String dateStyle;
  private String communicationStyle;
  private String relationshipView;
  private String selfIntroduction;


  public Profile toEntity(String userId) {
    return Profile.builder()
        .userId(userId)
        .annualIncome(this.annualIncome)
        .body_type(this.bodyType)
        .childPlan(this.childPlan)
        .communicationStyle(this.communicationStyle)
        .company(this.company)
        .coreValues(this.coreValues)
        .height(this.height)
        .dateStyle(this.dateStyle)
        .drinkingFrequency(this.drinkingFrequency)
        .education(this.education)
        .mbti(this.mbti)
        .hobbiesInterests(this.hobbiesInterests)
        .lifePriorities(this.lifePriorities)
        .marriageThoughts(this.marriageThoughts)
        .politicalTendency(this.politicalTendency)
        .personalKeywords(this.personalKeywords)
        .smoking(this.smoking)
        .relationshipView(this.relationshipView)
        .selfIntroduction(this.selfIntroduction)
        .weekendStyle(this.weekendStyle)
        .religion(this.religion)
        .build();
  }
}
