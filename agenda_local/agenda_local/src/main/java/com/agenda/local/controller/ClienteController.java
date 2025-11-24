package com.agenda.local.controller;

import com.agenda.local.model.Cliente;
import com.agenda.local.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repo;

    public ClienteController(ClienteRepository repo) { this.repo = repo; }

    @PostMapping
    public Cliente criar(@RequestBody Cliente c) { return repo.save(c); }

    @GetMapping
    public List<Cliente> listar() { return repo.findAll(); }
}
