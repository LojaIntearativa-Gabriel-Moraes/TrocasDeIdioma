package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.mapper.ProfessorMapper;
import com.example.TrocaDeIdioma.model.Permissao;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.Response.IncluirUsuarioRequest;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.repository.ProfessorRepository;
import com.example.TrocaDeIdioma.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

  @Autowired
  private ProfessorService professorService;

  @Autowired
  private ProfessorRepository professorRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody IncluirUsuarioRequest request) {
    if (professorRepository.findByEmail(request.getEmail()) != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
    }

    request.setSenha(passwordEncoder.encode(request.getSenha()));

    Permissao permissao = new Permissao();
    permissao.setNome("USER");


    Professor usuario = ProfessorMapper.toEntity(request);
    usuario.setSaldo(BigDecimal.ZERO);
    usuario.setPermissoes(new ArrayList<Permissao>());
    usuario.getPermissoes().add(permissao);
    professorRepository.save(usuario);

    return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
  }

  @GetMapping("available/{language}")
  public List<ProfessorResponse> findAvailableUsersByLanguage(@PathVariable String language, @RequestParam LocalTime startTime, @RequestParam LocalTime endTime, @RequestParam String dia) {
    return professorService.findAvailableProfsByLanguage(language,startTime, endTime, dia);
  }
}
