package com.example.Troca.Idiomas.webSocketConfig;

import com.example.Troca.Idiomas.model.Conversation;
import com.example.Troca.Idiomas.model.User;
import com.example.Troca.Idiomas.repository.ConversationRepository;
import com.example.Troca.Idiomas.service.security.BuscarUsuarioSecuritySerivce;
import com.example.Troca.Idiomas.service.security.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Optional;

@Component
public class ConversationHandler extends TextWebSocketHandler {

  private SimpMessagingTemplate messagingTemplate;

  @Autowired
  private ConversationRepository conversationRepository;

  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;

  @Autowired
  public ConversationHandler(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    Long conversationId = getConversationIdFromUri(session.getUri().toString());
    User user = usuarioAutenticadoService.get();
    if (conversationId == null) {
      throw new IllegalArgumentException("O ID da conversa não foi fornecido.");
    }

    // Busca a conversa pelo ID no banco de dados
    Optional<Conversation> conversation = conversationRepository.findById(conversationId);

    if (conversation.isEmpty()) {
      throw new IllegalArgumentException("A conversa não existe.");
    }

    // Adiciona o usuário à sala de chat correspondente
    session.getAttributes().put("conversationId", conversationId);
    session.getAttributes().put("user", user);

    String chatDestination = "/topic/conversations/" + conversationId;
    messagingTemplate.convertAndSend(chatDestination, "Usuário entrou na sala.");
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    Long conversationId = (Long) session.getAttributes().get("conversationId");

    if (conversationId == null) {
      throw new IllegalArgumentException("O ID da conversa não foi fornecido.");
    }

    String chatDestination = "/topic/conversations/" + conversationId;
    String sender = ((User) session.getAttributes().get("user")).getEmail();

    messagingTemplate.convertAndSend(chatDestination, sender + ": " + message.getPayload());
  }

  private Long getConversationIdFromUri(String uri) {
    // Extrai o ID da conversa da URL WebSocket
    // Exemplo: /ws/conversations/1234 -> 1234
    String[] parts = uri.split("/");
    String conversationIdString = parts[parts.length - 1];
    try {
      return Long.parseLong(conversationIdString);
    } catch (NumberFormatException ex) {
      return null;
    }
  }
}
