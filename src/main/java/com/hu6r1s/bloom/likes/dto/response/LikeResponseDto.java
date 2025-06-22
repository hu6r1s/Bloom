package com.hu6r1s.bloom.likes.dto.response;

import com.hu6r1s.bloom.likes.entity.LikeStatus;

public record LikeResponseDto(
    LikeStatus status,
    String chatRoomId
) {

}
