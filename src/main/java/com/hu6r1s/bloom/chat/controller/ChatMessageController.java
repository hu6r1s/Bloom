package com.hu6r1s.bloom.chat.controller;

import com.hu6r1s.bloom.chat.dto.response.ChatMessageDto;
import com.hu6r1s.bloom.chat.entity.ChatMessage;
import com.hu6r1s.bloom.chat.entity.ChatNotification;
import com.hu6r1s.bloom.chat.service.ChatMessageService;
import com.hu6r1s.bloom.chat.service.ChatRoomService;
import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

  private final SimpMessageSendingOperations messagingTemplate;
  private final ChatMessageService chatMessageService;
  private final ChatRoomService chatRoomService;

  @MessageMapping("/chat/message")
  public void message(@Payload ChatMessageDto messageDto, Authentication authentication) {
    String senderId = ((CustomUserDetails) authentication.getPrincipal()).getUser().getId();
    ChatMessage savedMessage = chatMessageService.saveMessage(messageDto, senderId);

    String roomId = savedMessage.getChatRoomId();
    Set<String> participantIds = chatRoomService.getParticipantIds(roomId);

    participantIds.stream()
        .filter(participantId -> ! participantId.equals(senderId))
        .forEach(participantId -> {
          messagingTemplate.convertAndSendToUser(
              participantId,
              "/queue/chat/room/" + roomId,
              new ChatNotification(
                  savedMessage.getChatRoomId(),
                  savedMessage.getSenderId(),
                  savedMessage.getSenderName(),
                  savedMessage.getContent()
              )
          );
            }

    );
  }
}
