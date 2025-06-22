package com.hu6r1s.bloom.chat.controller;

import com.hu6r1s.bloom.chat.dto.response.ChatRoomListDto;
import com.hu6r1s.bloom.chat.service.ChatRoomService;
import com.hu6r1s.bloom.users.entity.CustomUserDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat/rooms")
@RequiredArgsConstructor
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  @GetMapping
  public ResponseEntity<List<ChatRoomListDto>> getMyChatRooms(
      Authentication authentication
  ) {
    String userId = ((CustomUserDetails) authentication.getPrincipal()).getUser().getId();
    List<ChatRoomListDto> chatRooms = chatRoomService.findMyChatRooms(userId);
    return ResponseEntity.ok(chatRooms);

  }
}
