package org.jyothi.java.todowebapp.resources;

import java.io.Serializable;
import java.util.Date;

import org.jyothi.java.todowebapp.enums.TaskStatus;
import org.jyothi.java.todowebapp.model.Task;

public class TaskRO implements Serializable {

	private static final long serialVersionUID = -2244588338339979996L;

	private Long id;
	private String description;
	private Date created;
	private TaskStatus status;

	public TaskRO() {
	}

	public TaskRO(Task t) {
		id = t.getId();
		description = t.getDescription();
		created = t.getCreated();
		status = t.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
}
