package com.example.Troca.Idiomas.webSocketConfig;

import com.example.Troca.Idiomas.handler.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Autowired
  private ChatWebSocketHandler chatWebSocketHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(chatWebSocketHandler, "/chat")
      .setAllowedOrigins("*")
      .withSockJS();
  }

  @Bean
  public WebSocketHandler chatWebSocketHandler() {
    return new ChatWebSocketHandler(messagingTemplate);
  }
}
