package com.example.TrocaDeIdioma.repository;

import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {


    Aluno findByEmail(String email);
}
