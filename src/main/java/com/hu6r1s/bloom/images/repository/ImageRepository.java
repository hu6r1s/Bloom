package com.hu6r1s.bloom.images.repository;

import com.hu6r1s.bloom.images.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {

}
