package org.jyothi.java.todowebapp.service;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jyothi.java.todowebapp.dao.TaskDAO;
import org.jyothi.java.todowebapp.exception.DataNotFoundException;
import org.jyothi.java.todowebapp.model.ErrorMessage;
import org.jyothi.java.todowebapp.model.Task;
import org.jyothi.java.todowebapp.resources.TaskRO;

public class TaskService {
	private static TaskService instance;
	private TaskDAO taskDAO = TaskDAO.getInstance();

	public List<TaskRO> getAllTasks() {
		return taskDAO.getTasks();
	}

	public List<TaskRO> getCompletedTasks() {
		return taskDAO.getCompletedTasks();
	}

	public List<TaskRO> getInCompleteTasks() {
		return taskDAO.getInCompleteTasks();
	}

	public TaskRO getTask(Long id) {
		ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "http://google.com");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();

		TaskRO task = taskDAO.findByID(id);
		if (task == null) {
			throw new DataNotFoundException("Item with id " + id + " not found");
		}
		return task;
	}

	public TaskRO addTask(TaskRO task) {
		return taskDAO.addTask(task);
	}

	public void updateTask(TaskRO task) {
		if (task.getId() == null || task.getId() <= 0) {
			return;
		}
		taskDAO.updateTask(task.getId(), task);
	}

	public TaskRO removeTask(Long id) {
		return taskDAO.deleteTask(id);
	}

	public static TaskService getInstance() {
		if (instance == null) {
			instance = new TaskService();
		}
		return instance;
	}
}
