package com.hu6r1s.bloom.chat.service;

import com.hu6r1s.bloom.chat.dto.response.ChatMessageDto;
import com.hu6r1s.bloom.chat.entity.ChatMessage;
import com.hu6r1s.bloom.chat.entity.ChatRoom;
import com.hu6r1s.bloom.chat.repository.ChatMessageRepository;
import com.hu6r1s.bloom.chat.repository.ChatRoomRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

  private final ChatMessageRepository chatMessageRepository;
  private final ChatRoomRepository chatRoomRepository;

  @Transactional
  public ChatMessage saveMessage(ChatMessageDto messageDto, String senderId) {
    ChatMessage chatMessage = ChatMessage.builder()
        .chatRoomId(messageDto.chatRoomId())
        .senderId(senderId)
//        .senderName(messageDto.senderName())
//        .recipientId(messageDto.recipientId())
//        .recipientName(messageDto.recipientName())
        .content(messageDto.content())
        .createdAt(LocalDateTime.now())
//        .status(messageDto.status())
        .build();

    ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

    ChatRoom chatRoom = chatRoomRepository.findById(savedMessage.getChatRoomId())
        .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));

    chatRoom.savedLastMessage(savedMessage.getContent(), savedMessage.getCreatedAt());

    return savedMessage;
  }
}
