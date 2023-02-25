package com.example.Troca.Idiomas.model;

import javax.persistence.*;


import java.time.LocalDateTime;
@Entity
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "conversation_id")
  private Conversation conversation;

  // getters and setters
}
