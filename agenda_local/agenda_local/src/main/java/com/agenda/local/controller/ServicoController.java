package com.agenda.local.controller;

import com.agenda.local.model.Servico;
import com.agenda.local.repository.ServicoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoRepository repo;

    public ServicoController(ServicoRepository repo) { this.repo = repo; }

    @PostMapping
    public Servico criar(@RequestBody Servico s) { return repo.save(s); }

    @GetMapping
    public List<Servico> listar() { return repo.findAll(); }
}
