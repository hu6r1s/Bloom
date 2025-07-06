package com.hu6r1s.bloom.images.dto.response;

import com.hu6r1s.bloom.images.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageResponseDto {
  private String id;
  private String url;

  public static ImageResponseDto fromEntity(Image image) {
    return new ImageResponseDto(image.getId(), image.getUrl());
  }
}
