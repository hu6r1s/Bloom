package com.hu6r1s.bloom.chat.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
@Document(collection = "chat_messages")
public class ChatMessage {

  @Id
  private String id;

  @Field("chat_room_id")
  private String chatRoomId;

  @Field("sender_id")
  private String senderId;

  @Field("sender_name")
  private String senderName;

  @Field("recipient_id")
  private String recipientId;

  @Field("recipient_name")
  private String recipientName;

  @Field("content")
  private String content;

  @CreatedDate
  @Field("created_at")
  private LocalDateTime createdAt;

  @Field("status")
  private MessageStatus status;
}
