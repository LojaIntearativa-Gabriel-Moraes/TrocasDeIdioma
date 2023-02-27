package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

  @Autowired
  private ProfessorService professorService;
  @GetMapping("available/{language}")
  public List<ProfessorResponse> findAvailableUsersByLanguage(@PathVariable String language, @RequestParam LocalTime startTime, @RequestParam LocalTime endTime, @RequestParam String dia) {
    return professorService.findAvailableProfsByLanguage(language,startTime, endTime, dia);
  }
}
