package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class TaskService {
    @PersistenceContext
    private EntityManager em;

    public TaskService(){
        System.out.println("TaskService initialized: " + this);
    }

    public List<Task> getAllTasks() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }
    public Task createTask(Task task) {
        em.persist(task);
        return task;
    }

    public Task updateTask(Task task) {
        return em.merge(task);
    }

    public Task getTaskById(int id) {
        return em.find(Task.class, id);
    }


    public void deleteTask(int id) {
        Task task = em.find(Task.class, id);
        if (task != null) {
            em.remove(task);
        }
    }
}
