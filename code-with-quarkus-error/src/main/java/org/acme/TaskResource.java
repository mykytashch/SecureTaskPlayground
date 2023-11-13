package org.acme;

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
    TaskRepository taskRepository;

    @GET
    public List<Task> listTasks() {
        return taskRepository.findAllTasks();
    }

    @POST
    public Task addTask(Task task) {
        return taskRepository.persistTask(task);
    }

    @GET
    @Path("/{id}")
    public Task getTask(@PathParam("id") Long id) {
        Task task = taskRepository.findTaskById(id);
        if (task == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        return task;
    }

    @PUT
    @Path("/{id}")
    public Task updateTask(@PathParam("id") Long id, Task task) {
        if (taskRepository.findTaskById(id) == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        task.setId(id);
        return taskRepository.updateTask(task);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        taskRepository.deleteTask(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
