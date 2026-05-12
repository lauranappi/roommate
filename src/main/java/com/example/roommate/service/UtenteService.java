package com.example.roommate.service;

import com.example.roommate.model.Utente;
import com.example.roommate.repository.UtenteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UtenteService(UtenteRepository utenteRepository, JwtService jwtService) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtService = jwtService;
    }

    public Utente registra(Utente utente) {
        String hashata = passwordEncoder.encode(utente.getPasswordHash());
        utente.setPasswordHash(hashata);
        return utenteRepository.save(utente);
    }

    public String login(String email, String password) {
        Utente utente = utenteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        if (!passwordEncoder.matches(password, utente.getPasswordHash())) {
            throw new RuntimeException("Password errata");
        }

        return jwtService.generaToken(email);
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }
}
