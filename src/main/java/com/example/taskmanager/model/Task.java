package com.example.taskmanager.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean completed;
    private String description;
    private String priority;
    private LocalDate createdAt;

    public Task(String name, String description, String priority,  boolean completed) {
        this.name = name;
        this.completed = completed;
        this.description = description;
        this.priority = priority;
        this.createdAt = LocalDate.now();
    }
    public Task() {

    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public boolean isCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
