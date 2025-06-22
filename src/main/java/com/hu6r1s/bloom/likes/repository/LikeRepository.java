package com.hu6r1s.bloom.likes.repository;

import com.hu6r1s.bloom.likes.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<Like, String> {

  boolean existsByLikingUserIdAndLikedUserId(String likingUserId, String likedUserId);
}
