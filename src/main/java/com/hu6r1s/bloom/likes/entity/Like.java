package com.hu6r1s.bloom.likes.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "likes")
public class Like {

  @Id
  private String id;

  @Field("likingUserId")
  private String likingUserId;

  @Field("likedUserId")
  private String likedUserId;

  @CreatedDate
  @Field("created_at")
  private LocalDateTime createdAt;

  public Like(String likingUserId, String likedUserId) {
    this.likingUserId = likingUserId;
    this.likedUserId = likedUserId;
  }
}
