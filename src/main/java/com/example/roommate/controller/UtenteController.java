package com.example.roommate.controller;

import com.example.roommate.model.Utente;
import com.example.roommate.service.UtenteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping
    public List<Utente> getAll() {
        return utenteService.findAll();
    }

    @PostMapping
    public Utente create(@RequestBody Utente utente) {
        return utenteService.registra(utente);
    }

    record LoginRequest(String email, String password) {}

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return utenteService.login(request.email(), request.password());
    }
}
