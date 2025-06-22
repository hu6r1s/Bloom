package com.hu6r1s.bloom.chat.repository;

import com.hu6r1s.bloom.chat.entity.ChatRoom;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

  List<ChatRoom> findByParticipantIds(String userId);
}
