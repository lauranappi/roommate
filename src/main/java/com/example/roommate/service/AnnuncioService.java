package com.example.roommate.service;

import com.example.roommate.model.Annuncio;
import com.example.roommate.model.Utente;
import com.example.roommate.repository.AnnuncioRepository;
import com.example.roommate.repository.UtenteRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnuncioService {

    private final AnnuncioRepository annuncioRepository;
    private final UtenteRepository utenteRepository;

    public AnnuncioService(AnnuncioRepository annuncioRepository, UtenteRepository utenteRepository) {
        this.annuncioRepository = annuncioRepository;
        this.utenteRepository = utenteRepository;
    }

    public Annuncio crea(Annuncio annuncio) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Utente autore = utenteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        annuncio.setAutore(autore);
        return annuncioRepository.save(annuncio);
    }

    public List<Annuncio> cercaPerCitta(String citta) {
        return annuncioRepository.findByCitta(citta);
    }
}
