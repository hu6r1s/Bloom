package com.hu6r1s.bloom.users.service;

import com.hu6r1s.bloom.global.exception.ProfileDuplicationException;
import com.hu6r1s.bloom.global.exception.ProfileNotfoundException;
import com.hu6r1s.bloom.users.dto.request.ProfileRequestDto;
import com.hu6r1s.bloom.users.entity.Profile;
import com.hu6r1s.bloom.users.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

  private final ProfileRepository profileRepository;

  @Transactional
  public void createProfile(String userId, ProfileRequestDto requestDto) {
    if (profileRepository.existsByUserId(userId)) {
      throw new ProfileDuplicationException("이미 프로필이 존재합니다.");
    }

    Profile profile = requestDto.toEntity(userId);

    profileRepository.save(profile);
  }

  @Transactional
  public void updateProfile(String userId, ProfileRequestDto requestDto) {
    Profile existingProfile = profileRepository.findByUserId(userId)
        .orElseThrow(() -> new ProfileNotfoundException("존재하지 않는 프로필입니다."));

    existingProfile.update(requestDto);
    profileRepository.save(existingProfile);
  }
}
