package com.agendalocal.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceItem {
    private static final AtomicLong SEQ = new AtomicLong(1);
    private final Long id = SEQ.getAndIncrement();
    private String name;
    private double price;
    private boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    public ServiceItem() {}
    public ServiceItem(String name, double price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; touch(); }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; touch(); }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; touch(); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    private void touch() { this.updatedAt = LocalDateTime.now(); }
    @Override public String toString() { return "ServiceItem{"+id+",'"+name+"',"+price+"}"; }
}
