package com.agendalocal.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Client {
    private static final AtomicLong SEQ = new AtomicLong(1);
    private final Long id = SEQ.getAndIncrement();
    private String name;
    private String email;
    private String phone;
    private boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    public Client() {}
    public Client(String name, String email, String phone) {
        this.name = name; this.email = email; this.phone = phone;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; touch(); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; touch(); }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; touch(); }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; touch(); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    private void touch() { this.updatedAt = LocalDateTime.now(); }
    @Override public String toString() {
        return "Client{"+id+",'"+name+"'}";
    }
}
