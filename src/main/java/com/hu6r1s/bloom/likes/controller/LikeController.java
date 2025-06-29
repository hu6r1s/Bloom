package com.hu6r1s.bloom.likes.controller;

import com.hu6r1s.bloom.likes.dto.response.LikeResponseDto;
import com.hu6r1s.bloom.likes.service.LikeService;
import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeController {

  private final LikeService likeService;

  @PostMapping("/{likedUserId}")
  public ResponseEntity<LikeResponseDto> pressLike(
      Authentication authentication,
      @PathVariable String likedUserId
  ) {
    String likingUserId = ((CustomUserDetails) authentication.getPrincipal()).getId();

    LikeResponseDto response = likeService.pressLike(likingUserId, likedUserId);
    return ResponseEntity.ok(response);
  }
}
