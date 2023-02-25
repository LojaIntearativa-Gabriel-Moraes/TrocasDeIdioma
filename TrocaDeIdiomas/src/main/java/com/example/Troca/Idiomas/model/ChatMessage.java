package com.example.Troca.Idiomas.model;


import jakarta.persistence.*;

@Entity
@Table(name = "chat_message")
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private User user;

  @Column(name = "content")
  private String content;

  public ChatMessage(User user, String content) {
    this.user = user;
    this.content = content;
  }

}


