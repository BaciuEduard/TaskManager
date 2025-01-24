package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {

    @Inject
    private TaskService taskService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> tasks = taskService.getAllTasks();

        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> task.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }
        String sort = request.getParameter("sort");
        if ("name".equalsIgnoreCase(sort)) {
            tasks.sort(Comparator.comparing(Task::getName));
        }
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    @Transactional
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("_method");
        if ("DELETE".equals(method)) {
            int id = Integer.parseInt(request.getParameter("id"));
            taskService.deleteTask(id);
        } else if ("UPDATE".equals(method)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Task task = taskService.getTaskById(id);
            if (task != null) {
                task.setCompleted(true);
                taskService.updateTask(task);
            }
        } else {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String priority = request.getParameter("priority");

            Task newTask = new Task(name, description, priority, false);
            taskService.createTask(newTask);
        }
        response.sendRedirect("TaskServlet");
    }


}

