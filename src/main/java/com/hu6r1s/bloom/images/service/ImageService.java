package com.hu6r1s.bloom.images.service;

import com.hu6r1s.bloom.global.exception.NotValidExtensionException;
import com.hu6r1s.bloom.images.entity.Image;
import com.hu6r1s.bloom.images.repository.ImageRepository;
import com.hu6r1s.bloom.users.entity.User;
import com.hu6r1s.bloom.users.repository.UserRepository;
import io.awspring.cloud.s3.S3Template;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

  private final S3Template s3Template;
  private final ImageRepository imageRepository;
  private final UserRepository userRepository;

  @Value("${spring.cloud.aws.s3.bucket}")
  private String bucket;

  private final String ACTIVE_IMAGE_DIR = "images/users/";

  @Transactional
  public void uploadImage(MultipartFile file, String userId)
      throws IOException, NotValidExtensionException {
    if (!file.getContentType().startsWith("image/")) {
      throw new NotValidExtensionException("이미지 파일만 업로드 가능합니다.");
    }
    String originalFileName = file.getOriginalFilename();
    String fileExtension = Optional.ofNullable(originalFileName)
        .filter(f -> f.contains("."))
        .map(f -> f.substring(originalFileName.lastIndexOf(".") + 1))
        .orElse("jpg");

    User user = userRepository.findById(userId).orElseThrow(() ->
        new UsernameNotFoundException("사용자를 찾을 수 없습니다.")
    );

    String fileName = UUID.randomUUID() + "." + fileExtension;
    String storedFileName = ACTIVE_IMAGE_DIR + userId + "::" + user.getNickname() + "/" + fileName;

    String imageUrl = s3Template.upload(bucket, storedFileName, file.getInputStream()).getURL()
        .toString();

    Image image = Image.builder()
        .userId(userId)
        .url(imageUrl)
        .storedFileName(storedFileName)
        .fileExtension(fileExtension)
        .originalFileName(originalFileName)
        .fileSize(file.getSize())
        .build();

    imageRepository.save(image);
  }
}
