package com.example.Troca.Idiomas.model;


import lombok.Data;

import jakarta.persistence.*;


@Entity
@Data
@Table(name = "chat_message")
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "content")
  private String content;

  public ChatMessage(Long userId, String content) {
    this.userId = userId;
    this.content = content;
  }

}


