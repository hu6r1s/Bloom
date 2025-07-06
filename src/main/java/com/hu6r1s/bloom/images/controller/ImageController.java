package com.hu6r1s.bloom.images.controller;

import com.hu6r1s.bloom.global.exception.NotValidExtensionException;
import com.hu6r1s.bloom.images.dto.response.ImageResponseDto;
import com.hu6r1s.bloom.images.service.ImageService;
import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

  private final ImageService imageService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> uploadImage(
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @RequestParam("file") MultipartFile file) throws IOException, NotValidExtensionException {

    imageService.uploadImage(file, userDetails.getName());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<ImageResponseDto>> findAllImage(
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    List<ImageResponseDto> images = imageService.findAllImage(userDetails.getName());
    return ResponseEntity.ok(images);
  }
}
