package com.hu6r1s.bloom.users.controller;

import com.hu6r1s.bloom.users.dto.response.UserInfoResponseDto;
import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import com.hu6r1s.bloom.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public ResponseEntity<UserInfoResponseDto> getMyInfo(Authentication authentication) {
    String userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
    UserInfoResponseDto userInfo = userService.getUserInfo(userId);
    return ResponseEntity.ok(userInfo);
  }
}
