package org.jyothi.java.todowebapp.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
//import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jyothi.java.todowebapp.model.Task;
import org.jyothi.java.todowebapp.service.TaskService;

@Path("tasks")
@XmlRootElement
public class TaskResource {
	TaskService taskService = TaskService.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaskRO> getTasks(@QueryParam("filter") String filter) {
		if ("COMPLETED".equalsIgnoreCase(filter)) {
			return getCompletedTasks();
		} else if ("INCOMPLETE".equalsIgnoreCase(filter)) {
			return getInCompleteTasks();
		}
		return taskService.getAllTasks();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TaskRO addTask(TaskRO task) throws URISyntaxException {

		return taskService.addTask(task);

	}

	@PUT
	@Path("/{taskid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateTask(@PathParam("taskid") Long id, TaskRO task) {
		task.setId(id);
		taskService.updateTask(task);
	}

	@DELETE
	@Path("/{taskid}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteTask(@PathParam("taskid") Long id) {
		taskService.removeTask(id);
	}

	@GET
	@Path("/{taskid}")
	@Produces(MediaType.APPLICATION_JSON)
	public TaskRO getTask(@PathParam("taskid") Long id) {
		TaskRO task = taskService.getTask(id);
		return task;
	}

	private List<TaskRO> getCompletedTasks() {
		return taskService.getCompletedTasks();
	}

	private List<TaskRO> getInCompleteTasks() {
		return taskService.getInCompleteTasks();
	}
}
