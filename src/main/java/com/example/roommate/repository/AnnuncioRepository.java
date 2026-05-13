package com.example.roommate.repository;

import com.example.roommate.model.Annuncio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnnuncioRepository extends JpaRepository<Annuncio, Long> {
    List<Annuncio> findByCitta(String citta);
}
