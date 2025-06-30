package com.hu6r1s.bloom.users.entity;

import com.hu6r1s.bloom.users.entity.enums.BodyType;
import com.hu6r1s.bloom.users.entity.enums.ChildPlan;
import com.hu6r1s.bloom.users.entity.enums.DrinkingFrequency;
import com.hu6r1s.bloom.users.entity.enums.Religion;
import com.hu6r1s.bloom.users.entity.enums.Smoking;
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
  private BodyType body_type;

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
}
