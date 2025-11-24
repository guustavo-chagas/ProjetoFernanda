package com.agenda.local.controller;

import com.agenda.local.model.Prestador;
import com.agenda.local.repository.PrestadorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {

    private final PrestadorRepository repo;

    public PrestadorController(PrestadorRepository repo) { this.repo = repo; }

    @PostMapping
    public Prestador criar(@RequestBody Prestador p) { return repo.save(p); }

    @GetMapping
    public List<Prestador> listar() { return repo.findAll(); }
}
