package org.jyothi.java.todowebapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.jyothi.java.todowebapp.enums.TaskStatus;

@Entity
@Table(name = "Todolist")
@XmlRootElement(name = "task")
public class Task implements Serializable {

	private static final long serialVersionUID = -3593370835225569871L;

	public static final String GET_ALL_TASKS = "from Task";
	public static final String GET_COMPLETED_TASKS = "from Task where status = org.jyothi.java.todowebapp.enums.TaskStatus.COMPLETE";
	public static final String GET_INCOMPLETE_TASKS = "from Task where status = org.jyothi.java.todowebapp.enums.TaskStatus.INCOMPLETE";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "DATE_CREATED")
	private Date created;
	@Column(name = "STATUS")
	private TaskStatus status;

	public Task() {
	}

	public Task(Long id, String description, TaskStatus status) {
		this.id = id;
		this.description = description;
		this.created = new Date();
		this.status = status;
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
