package com.agenda.local.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private Boolean ativo = true;

    private LocalDateTime criadoEm = LocalDateTime.now();
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    @PreUpdate
    public void atualizarTimestamp() {
        atualizadoEm = LocalDateTime.now();
    }
}
