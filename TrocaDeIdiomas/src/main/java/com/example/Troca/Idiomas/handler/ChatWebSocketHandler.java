package com.example.Troca.Idiomas.handler;

import com.example.Troca.Idiomas.model.ChatMessage;
import com.example.Troca.Idiomas.model.User;
import com.example.Troca.Idiomas.service.security.UsuarioAutenticadoService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ChatWebSocketHandler extends TextWebSocketHandler {

  private SimpMessagingTemplate messagingTemplate;
  private UsuarioAutenticadoService usuarioAutenticadoService;


  public ChatWebSocketHandler(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    // Obtém o usuário atual a partir da classe UsuarioAutenticadoService
    Long userId = usuarioAutenticadoService.getId();

    // Adiciona o usuário à lista de usuários conectados
    ConnectedUsers.add(userId, session);

    // Envia uma mensagem de notificação para todos os usuários conectados
    messagingTemplate.convertAndSend("/topic/users", ConnectedUsers.getConnectedUsers());
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    User user = (User) session.getAttributes().get("user");
    String content = message.getPayload();
    ChatMessage chatMessage = new ChatMessage(user.getId(), content);
    messagingTemplate.convertAndSend("/topic/chat", chatMessage);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    // Obtém o usuário atual a partir da classe UsuarioAutenticadoService
    Long userId = usuarioAutenticadoService.getId();

    // Remove o usuário da lista de usuários conectados
    ConnectedUsers.remove(userId, session);

    // Envia uma mensagem de notificação para todos os usuários conectados
    messagingTemplate.convertAndSend("/topic/users", ConnectedUsers.getConnectedUsers());
  }
}
