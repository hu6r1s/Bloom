package com.hu6r1s.bloom.auth.service;

import com.hu6r1s.bloom.global.jwt.JwtProvider;
import com.hu6r1s.bloom.users.dto.request.UserSignupRequestDto;
import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import com.hu6r1s.bloom.users.entity.User;
import com.hu6r1s.bloom.users.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final JwtProvider jwtProvider;

  @Transactional
  public Map<String, String> completeRegistration(String userId, UserSignupRequestDto requestDto) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

    user.completeUserRegistration(
        requestDto.getNickname(),
        requestDto.getAge(),
        requestDto.getGender(),
        requestDto.getResidenceRegion(),
        requestDto.getOccupation()
    );

    userRepository.save(user);

    CustomUserDetails userDetails = CustomUserDetails.builder()
        .id(user.getId())
        .email(user.getEmail())
        .role(user.getRoles())
        .isActive(user.isActive())
        .build();
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    String accessToken = jwtProvider.createAccessToken(authentication);
    String refreshToken = jwtProvider.createRefreshToken(authentication);

    Map<String, String> tokens = new HashMap<>();
    tokens.put("accessToken", accessToken);
    tokens.put("refreshToken", refreshToken);
    return tokens;
  }
}
