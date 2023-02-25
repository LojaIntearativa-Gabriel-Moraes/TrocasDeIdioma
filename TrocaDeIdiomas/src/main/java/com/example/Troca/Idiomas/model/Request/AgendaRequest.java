package com.example.Troca.Idiomas.model.Request;

import com.example.Troca.Idiomas.model.User;

import java.time.LocalDateTime;

public class AgendaRequest {
  private User user;
  private User partner;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getPartner() {
    return partner;
  }

  public void setPartner(User partner) {
    this.partner = partner;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }
}
