package com.agendalocal.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class OrderService {
    private static final AtomicLong SEQ = new AtomicLong(1);
    private final Long id = SEQ.getAndIncrement();
    private Long appointmentId;
    private double amount;
    private boolean paid = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private Payment payment;
    public OrderService() {}
    public OrderService(Long appointmentId, double amount) { this.appointmentId = appointmentId; this.amount = amount; }
    public Long getId() { return id; }
    public Long getAppointmentId() { return appointmentId; }
    public double getAmount() { return amount; }
    public boolean isPaid() { return paid; }
    public void markPaid(Payment p) { this.paid = true; this.payment = p; touch(); }
    public Payment getPayment() { return payment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    private void touch() { this.updatedAt = LocalDateTime.now(); }
    @Override public String toString() { return "OrderService{"+id+", amt="+amount+",paid="+paid+"}"; }
}
