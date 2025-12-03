package com.agendalocal.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Appointment {
    private static final AtomicLong SEQ = new AtomicLong(1);

    public enum Status { SCHEDULED, CONFIRMED, CANCELED, COMPLETED }

    private final Long id = SEQ.getAndIncrement();
    private Long clientId;
    private Long providerId;
    private Long serviceId;
    private LocalDateTime when;
    private Status status = Status.SCHEDULED;
    private String cancelReason;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private OrderService order;

    public Appointment() {}

    public Appointment(Long clientId, Long providerId, Long serviceId, LocalDateTime when) {
        this.clientId = clientId;
        this.providerId = providerId;
        this.serviceId = serviceId;
        this.when = when;
    }

    public Long getId() { return id; }
    public Long getClientId() { return clientId; }
    public Long getProviderId() { return providerId; }
    public Long getServiceId() { return serviceId; }
    public LocalDateTime getWhen() { return when; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; touch(); }
    public String getCancelReason() { return cancelReason; }
    public void setCancelReason(String cancelReason) { this.cancelReason = cancelReason; touch(); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setOrder(OrderService order) { this.order = order; touch(); }
    public OrderService getOrder() { return order; }

    private void touch() { this.updatedAt = LocalDateTime.now(); }

    @Override
    public String toString() {
        return "Agendamento Nº " + id + "\n" +
                "Data e Hora: " + when + "\n" +
                "Situação: " + status +
                (cancelReason != null ? "\nMotivo do cancelamento: " + cancelReason : "") +
                "\n";
    }
}
