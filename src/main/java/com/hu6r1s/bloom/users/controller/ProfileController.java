package com.hu6r1s.bloom.users.controller;

import com.hu6r1s.bloom.users.dto.request.ProfileRequestDto;
import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import com.hu6r1s.bloom.users.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

  private final ProfileService profileService;

  @PostMapping("/me")
  public ResponseEntity<Void> createProfile(
      Authentication authentication,
      @RequestBody ProfileRequestDto requestDto) {
    String userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
    profileService.createProfile(userId, requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PatchMapping("/me")
  public ResponseEntity<Void> updateMyProfile(
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @RequestBody ProfileRequestDto requestDto) {

    profileService.updateProfile(userDetails.getId(), requestDto);

    return ResponseEntity.ok().build();
  }
}
