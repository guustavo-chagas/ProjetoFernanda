package com.agenda.local.repository;

import com.agenda.local.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByPrestadorIdAndHorario(Long prestadorId, java.time.LocalDateTime horario);
}
