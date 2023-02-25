package com.example.Troca.Idiomas.repository;

import com.example.Troca.Idiomas.model.Conversation;
import com.example.Troca.Idiomas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

  List<Conversation> findBySenderOrRecipientOrderByCreatedAtDesc(User sender, User recipient);
}
