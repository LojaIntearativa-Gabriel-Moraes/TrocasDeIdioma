package com.example.TrocaDeIdioma.repository;


import com.example.TrocaDeIdioma.model.SolicitacaoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoAulaRepository extends JpaRepository<SolicitacaoAula, Long> {

}
