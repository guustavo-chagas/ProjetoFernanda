package com.agendalocal.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Payment {
    private static final AtomicLong SEQ = new AtomicLong(1);
    private final Long id = SEQ.getAndIncrement();
    private double amount;
    private String method;
    private LocalDateTime paidAt = LocalDateTime.now();
    public Payment() {}
    public Payment(double amount, String method) { this.amount = amount; this.method = method; }
    public Long getId() { return id; }
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public LocalDateTime getPaidAt() { return paidAt; }
    @Override public String toString() { return "Payment{"+id+","+amount+","+method+"}"; }
}
