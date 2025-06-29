package com.hu6r1s.bloom.users.service;

import com.hu6r1s.bloom.users.dto.response.UserInfoResponseDto;
import com.hu6r1s.bloom.users.entity.User;
import com.hu6r1s.bloom.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserInfoResponseDto getUserInfo(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    return UserInfoResponseDto.builder()
        .id(user.getId())
        .nickname(user.getNickname())
        .age(user.getAge())
        .residenceRegion(user.getResidenceRegion())
        .roles(user.getRoles())
        .occupation(user.getOccupation())
        .gender(user.getGender())
        .phoneVerified(user.isPhoneVerified())
        .build();
  }
}
