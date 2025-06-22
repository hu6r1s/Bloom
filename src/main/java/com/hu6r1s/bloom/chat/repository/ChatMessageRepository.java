package com.hu6r1s.bloom.chat.repository;

import com.hu6r1s.bloom.chat.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

}
