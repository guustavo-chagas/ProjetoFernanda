package com.agenda.local.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Prestador prestador;

    @ManyToOne
    private Servico servico;

    private LocalDateTime horario;

    private Boolean confirmado = false;

    private LocalDateTime criadoEm = LocalDateTime.now();
}
