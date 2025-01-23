package com.example.taskmanager.rest;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {
    @Inject
    private TaskService taskService;

    @GET
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTask(Task task) {
        Task created = taskService.createTask(task);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    public Task updateTask(Task task) {
        return taskService.updateTask(task);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") int id) {
        taskService.deleteTask(id);
        return Response.noContent().build();
    }
}
