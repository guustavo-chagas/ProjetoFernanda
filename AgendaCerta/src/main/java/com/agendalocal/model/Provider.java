package com.agendalocal.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Provider {
    private static final AtomicLong SEQ = new AtomicLong(1);
    private final Long id = SEQ.getAndIncrement();
    private String name;
    private boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    public Provider() {}
    public Provider(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; touch(); }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; touch(); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    private void touch() { this.updatedAt = LocalDateTime.now(); }
    @Override public String toString() {
        return "Provider{"+id+",'"+name+"'}";
    }
}
