package com.hu6r1s.bloom.global.util;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

  private final RedisTemplate<String, String> redisTemplate;

  public void saveToken(String userId, String refreshToken, long validity) {
    redisTemplate.opsForValue().set(
        userId,
        refreshToken,
        validity,
        TimeUnit.SECONDS
    );
  }

  public String getToken(String userId) {
    return redisTemplate.opsForValue().get(userId);
  }

  public void deleteToken(String userId) {
    redisTemplate.delete(userId);
  }
}
