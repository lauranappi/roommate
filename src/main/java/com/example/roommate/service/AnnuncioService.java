package com.example.roommate.service;

import com.example.roommate.model.Annuncio;
import com.example.roommate.repository.AnnuncioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnuncioService {

    private final AnnuncioRepository annuncioRepository;

    public AnnuncioService(AnnuncioRepository annuncioRepository) {
        this.annuncioRepository = annuncioRepository;
    }

    public Annuncio crea(Annuncio annuncio) {
        return annuncioRepository.save(annuncio);
    }

    public List<Annuncio> cercaPerCitta(String citta) {
        return annuncioRepository.findByCitta(citta);
    }
}
