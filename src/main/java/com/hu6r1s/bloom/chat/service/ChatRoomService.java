package com.hu6r1s.bloom.chat.service;

import com.hu6r1s.bloom.chat.dto.response.ChatRoomListDto;
import com.hu6r1s.bloom.chat.entity.ChatRoom;
import com.hu6r1s.bloom.chat.repository.ChatRoomRepository;
import com.hu6r1s.bloom.global.exception.ChatPartnerNotFoundException;
import com.hu6r1s.bloom.global.exception.NotFoundChatRoomException;
import com.hu6r1s.bloom.users.entity.User;
import com.hu6r1s.bloom.users.repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

  private final ChatRoomRepository chatRoomRepository;
  private final UserRepository userRepository;

  public List<ChatRoomListDto> findMyChatRooms(String userId) {
    List<ChatRoom> myRooms = chatRoomRepository.findByParticipantIds(userId);

    return myRooms.stream()
        .map(room -> {
          String partnerId = room.getParticipantIds().stream()
              .filter(id -> !id.equals(userId))
              .findFirst()
              .orElseThrow(() -> new ChatPartnerNotFoundException("채팅방에 상대방이 없습니다."));

          User partner = userRepository.findById(partnerId)
              .orElseThrow(() -> new ChatPartnerNotFoundException("상대방 유저를 찾을 수 없습니다."));

          return new ChatRoomListDto(room.getId(), userId, partner.getNickname());
        })
        .collect(Collectors.toList());
  }

  public Set<String> getParticipantIds(String roomId) {
    return chatRoomRepository.findById(roomId)
        .map(ChatRoom::getParticipantIds)
        .orElseThrow(() -> new NotFoundChatRoomException("존재하지 않는 채팅방입니다."));
  }
}
