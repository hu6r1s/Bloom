package com.hu6r1s.bloom.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatNotification {

  private String id;
  private String senderId;
  private String senderName;
  private String content;
}
