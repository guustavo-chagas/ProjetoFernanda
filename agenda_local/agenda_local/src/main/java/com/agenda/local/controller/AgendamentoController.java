package com.agenda.local.controller;

import com.agenda.local.model.Agendamento;
import com.agenda.local.repository.AgendamentoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoRepository repo;

    public AgendamentoController(AgendamentoRepository repo) { this.repo = repo; }

    @PostMapping
    public String criar(@RequestBody Agendamento a) {

        boolean ocupado = repo.existsByPrestadorIdAndHorario(a.getPrestador().getId(), a.getHorario());

        if (ocupado) {
            return "Erro: Prestador já possui agendamento neste horário.";
        }

        repo.save(a);
        return "Agendamento criado com sucesso!";
    }

    @GetMapping
    public List<Agendamento> listar() { return repo.findAll(); }
}
