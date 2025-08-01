package com.hu6r1s.bloom.users.repository;

import com.hu6r1s.bloom.users.entity.Profile;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {

  boolean existsByUserId(String userId);

  Optional<Profile> findByUserId(String userId);
}
