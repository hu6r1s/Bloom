package com.hu6r1s.bloom.users.repository;

import com.hu6r1s.bloom.users.entity.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(String email);
}
