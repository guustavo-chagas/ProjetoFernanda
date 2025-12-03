package com.agendalocal.service;

import org.springframework.stereotype.Service;

import com.agendalocal.model.*;

@Service
public class ReportService {
    private final AgendaService agendaService;

    public ReportService(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    public void printSimpleReport() {
        System.out.println("--- Clientes cadastrados ---");
        agendaService.listClients().forEach(System.out::println);
        System.out.println("--- Prestadores ---");
        agendaService.listProviders().forEach(System.out::println);
        System.out.println("--- Serviços ---");
        agendaService.listServices().forEach(System.out::println);
        System.out.println("--- Agendamentos ---");
        agendaService.listAppointments().forEach(System.out::println);
    }
}
