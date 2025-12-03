package com.agendalocal.service;

import org.springframework.stereotype.Service;

import com.agendalocal.model.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AgendaService {
    private final Map<Long, Client> clients = new HashMap<>();
    private final Map<Long, Provider> providers = new HashMap<>();
    private final Map<Long, ServiceItem> services = new HashMap<>();
    private final Map<Long, Appointment> appointments = new HashMap<>();
    private final Map<Long, OrderService> orders = new HashMap<>();

    // RF001
    public Client createClient(String name, String email, String phone) {
        Client c = new Client(name, email, phone);
        clients.put(c.getId(), c);
        return c;
    }

    // RF002
    public Provider createProvider(String name) {
        Provider p = new Provider(name);
        providers.put(p.getId(), p);
        return p;
    }

    // RF003
    public ServiceItem createService(String name, double price) {
        ServiceItem s = new ServiceItem(name, price);
        services.put(s.getId(), s);
        return s;
    }

    // RF004 & RF005
    public Appointment scheduleAppointment(Long clientId, Long providerId, Long serviceId, String whenIso) {
        Client c = clients.get(clientId);
        if (c == null || !c.isActive()) throw new RuntimeException("Cliente inexistente ou inativo");
        Provider p = providers.get(providerId);
        if (p == null || !p.isActive()) throw new RuntimeException("Prestador inexistente ou inativo");
        ServiceItem s = services.get(serviceId);
        if (s == null || !s.isActive()) throw new RuntimeException("Serviço inexistente ou inativo");
        LocalDateTime when = LocalDateTime.parse(whenIso);
        // regra: um horário não pode ser agendado duas vezes para o mesmo prestador
        boolean occupied = appointments.values().stream()
            .anyMatch(a -> a.getProviderId().equals(providerId) && a.getWhen().equals(when) && a.getStatus() != Appointment.Status.CANCELED);
        if (occupied) throw new RuntimeException("Horário já ocupado para este prestador");
        Appointment ap = new Appointment(clientId, providerId, serviceId, when);
        appointments.put(ap.getId(), ap);
        return ap;
    }

    // RF006
    public void confirmAppointment(Long appointmentId) {
        Appointment ap = appointments.get(appointmentId);
        if (ap == null) throw new RuntimeException("Agendamento não encontrado");
        ap.setStatus(Appointment.Status.CONFIRMED);
        // gerar Ordem de Serviço automaticamente
        ServiceItem s = services.get(ap.getServiceId());
        OrderService o = new OrderService(ap.getId(), s.getPrice());
        orders.put(o.getId(), o);
        ap.setOrder(o);
    }

    // RF007
    public void registerPayment(Long appointmentId, double amount, String method) {
        Appointment ap = appointments.get(appointmentId);
        if (ap == null) throw new RuntimeException("Agendamento não encontrado");
        OrderService o = ap.getOrder();
        if (o == null) throw new RuntimeException("Ordem de serviço não encontrada para este agendamento");
        if (o.isPaid()) throw new RuntimeException("Ordem já paga");
        if (amount < o.getAmount()) throw new RuntimeException("Valor insuficiente");
        Payment p = new Payment(amount, method);
        o.markPaid(p);
    }

    // RF008
    public void cancelAppointment(Long appointmentId, String justification) {
        if (justification == null || justification.trim().isEmpty()) {
            throw new RuntimeException("Cancelamento exige justificativa");
        }

        Appointment ap = appointments.get(appointmentId);
        if (ap == null) {
            throw new RuntimeException("Agendamento não encontrado");
        }

        ap.setStatus(Appointment.Status.CANCELED);
        ap.setCancelReason(justification);
    }


    // Finalizar ordem (somente após pagamento)
    public void finalizeOrder(Long appointmentId) {
        Appointment ap = appointments.get(appointmentId);
        if (ap == null) throw new RuntimeException("Agendamento não encontrado");
        OrderService o = ap.getOrder();
        if (o == null) throw new RuntimeException("Ordem de serviço inexistente");
        if (!o.isPaid()) throw new RuntimeException("A Ordem de Serviço só pode ser finalizada após o pagamento");
        ap.setStatus(Appointment.Status.COMPLETED);
    }

    // regra 6: não excluir clientes com serviços em andamento (apenas desativar)
    public void deactivateClient(Long clientId) {
        Client c = clients.get(clientId);
        if (c == null) throw new RuntimeException("Cliente não encontrado");
        boolean hasOngoing = appointments.values().stream()
            .anyMatch(a -> a.getClientId().equals(clientId) && a.getStatus() == Appointment.Status.CONFIRMED);
        if (hasOngoing) throw new RuntimeException("Não é permitido desativar/excluir cliente com serviços em andamento");
        c.deactivate();
    }

    // utilitários para relatórios e consulta
    public List<Appointment> listAppointments() { return new ArrayList<>(appointments.values()); }
    public List<Client> listClients() { return new ArrayList<>(clients.values()); }
    public List<Provider> listProviders() { return new ArrayList<>(providers.values()); }
    public List<ServiceItem> listServices() { return new ArrayList<>(services.values()); }
    public Optional<Appointment> findAppointment(Long id) { return Optional.ofNullable(appointments.get(id)); }
}
