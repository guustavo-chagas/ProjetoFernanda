package com.agendalocal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.agendalocal.model.Client;
import com.agendalocal.model.Provider;
import com.agendalocal.model.ServiceItem;
import com.agendalocal.model.Appointment;
import com.agendalocal.service.AgendaService;
import com.agendalocal.service.ReportService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner run(AgendaService agendaService, ReportService reportService) {
        return args -> {
            System.out.println("=== Agenda Local - Simulação ===");

            Client c1 = agendaService.createClient("Ana Silva", "ana@example.com", "11999990000");
            Client c2 = agendaService.createClient("Bruno Lima", "bruno@example.com", "11988880000");

            Provider p1 = agendaService.createProvider("Carlos Barbearia");
            Provider p2 = agendaService.createProvider("Dona Manicure");

            ServiceItem s1 = agendaService.createService("Corte de Cabelo", 50.0);
            ServiceItem s2 = agendaService.createService("Manicure Simples", 30.0);

            Appointment ap1 = agendaService.scheduleAppointment(
                    c1.getId(),
                    p1.getId(),
                    s1.getId(),
                    "2025-12-05T10:00"
            );

            try {
                Appointment apFail = agendaService.scheduleAppointment(
                        c2.getId(),
                        p1.getId(),
                        s1.getId(),
                        "2025-12-05T10:00"
                );
            } catch (RuntimeException ex) {
                System.out.println("Esperado: falha ao agendar horário já ocupado -> " + ex.getMessage());
            }

            agendaService.confirmAppointment(ap1.getId());

            try {
                agendaService.finalizeOrder(ap1.getId());
            } catch (RuntimeException ex) {
                System.out.println("Esperado: não finalizar sem pagamento -> " + ex.getMessage());
            }

            agendaService.registerPayment(ap1.getId(), 50.0, "PIX");
            agendaService.finalizeOrder(ap1.getId());

            Appointment ap2 = agendaService.scheduleAppointment(
                    c2.getId(),
                    p2.getId(),
                    s2.getId(),
                    "2025-12-06T14:00"
            );

            agendaService.cancelAppointment(ap2.getId(), "Imprevisto");

            System.out.println("Relatório simples:");
            reportService.printSimpleReport();

            System.out.println("Concluído");
        };
    }
}
