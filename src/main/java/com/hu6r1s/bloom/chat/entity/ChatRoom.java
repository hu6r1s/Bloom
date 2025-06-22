package com.hu6r1s.bloom.chat.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "chat_rooms")
public class ChatRoom {

  @Id
  private String id;

  @Field("participant_ids")
  private Set<String> participantIds = new HashSet<>();

  @CreatedDate
  @Field("created_at")
  private LocalDateTime createdAt;

  @Field("last_message")
  private String lastMessage;

  @Field("last_message_at")
  private LocalDateTime lastMessageAt;

  public ChatRoom(Set<String> participantIds) {
    this.participantIds = participantIds;
  }

  public void savedLastMessage(String lastMessage, LocalDateTime lastMessageAt) {
    this.lastMessage = lastMessage;
    this.lastMessageAt = lastMessageAt;
  }
}
