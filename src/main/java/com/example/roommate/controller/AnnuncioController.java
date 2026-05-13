package com.example.roommate.controller;

import com.example.roommate.model.Annuncio;
import com.example.roommate.service.AnnuncioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annunci")
public class AnnuncioController {

    private final AnnuncioService annuncioService;

    public AnnuncioController(AnnuncioService annuncioService) {
        this.annuncioService = annuncioService;
    }

    @GetMapping
    public List<Annuncio> getAnnuncioByCitta(@RequestParam String citta) {
        return annuncioService.cercaPerCitta(citta);
    }

    @PostMapping
    public Annuncio create(@RequestBody Annuncio annuncio){
        return annuncioService.crea(annuncio);
    }
}
