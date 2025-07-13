package com.hu6r1s.bloom.images.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "images")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Image {

  @Id
  private String id;

  @Indexed
  @Field("user_id")
  private String userId;

  @Field("url")
  private String url;

  @Field("original_file_name")
  private String originalFileName;

  @Field("stored_file_name")
  private String storedFileName;

  @Field("file_size")
  private long fileSize;

  @Field("file_extension")
  private String fileExtension;

  @Field("is_active")
  private boolean isActive;

  @CreatedDate
  @Field("created_at")
  private LocalDateTime createdAt;

  @Field("deleted_at")
  private LocalDateTime deletedAt;
}
