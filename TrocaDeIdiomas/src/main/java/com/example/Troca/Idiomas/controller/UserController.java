package com.example.Troca.Idiomas.controller;

import com.example.Troca.Idiomas.model.User;
import com.example.Troca.Idiomas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    // Verifica se o e-mail já existe no banco de dados
    if (userRepository.findByEmail(user.getEmail()) != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
    }

    // Criptografa a senha do usuário antes de salvar no banco de dados
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

    // Salva o usuário no banco de dados
    User savedUser = userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    // Busca o usuário pelo ID no banco de dados
    Optional<User> user = userRepository.findById(id);

    // Verifica se o usuário foi encontrado
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
    }
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    // Busca todos os usuários no banco de dados
    List<User> users = userRepository.findAll();

    return ResponseEntity.ok(users);
  }

  // outros métodos do controlador, como atualização e exclusão do usuário
}
