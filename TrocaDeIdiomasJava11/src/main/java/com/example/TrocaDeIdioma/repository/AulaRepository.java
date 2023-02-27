package com.example.TrocaDeIdioma.repository;

import com.example.TrocaDeIdioma.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

}
