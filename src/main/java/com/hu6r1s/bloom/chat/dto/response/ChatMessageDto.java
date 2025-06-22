package com.hu6r1s.bloom.chat.dto.response;

import com.hu6r1s.bloom.chat.entity.MessageStatus;
import java.time.LocalDateTime;

public record ChatMessageDto(
    String chatRoomId,
    String content,
//    String recipientId,
//    String senderId,
//    String senderName,
//    String recipientName,
//    MessageStatus status,
    LocalDateTime createdAt
) {

}
