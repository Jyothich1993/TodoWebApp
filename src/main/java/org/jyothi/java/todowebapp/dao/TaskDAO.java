package org.jyothi.java.todowebapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jyothi.java.todowebapp.model.Task;
import org.jyothi.java.todowebapp.persistence.HibernateUtil;
import org.jyothi.java.todowebapp.resources.TaskRO;

public class TaskDAO {

	private static TaskDAO instance;

	private TaskDAO() {
	}

	public static TaskDAO getInstance() {
		if (instance == null) {
			instance = new TaskDAO();
		}

		return instance;
	}

	public TaskRO findByID(Long id) {
		Session session = HibernateUtil.openSession();
		TaskRO i = new TaskRO((Task) session.get(Task.class, id));
		HibernateUtil.closeSession(session);
		return i;
	}

	public List<TaskRO> getTasks() {
		Session session = HibernateUtil.openSession();
		Query query = session.createQuery(Task.GET_ALL_TASKS);
		List<Task> tasks = query.list();
		List<TaskRO> taskros = new ArrayList<TaskRO>();
		for (Task task : tasks) {
			taskros.add(new TaskRO(task));
		}
		HibernateUtil.closeSession(session);
		return taskros;
	}

	public List<TaskRO> getCompletedTasks() {
		Session session = HibernateUtil.openSession();
		Query query = session.createQuery(Task.GET_COMPLETED_TASKS);
		List<Task> tasks = query.list();
		List<TaskRO> taskros = new ArrayList<TaskRO>();
		for (Task task : tasks) {
			taskros.add(new TaskRO(task));
		}
		HibernateUtil.closeSession(session);
		return taskros;
	}

	public List<TaskRO> getInCompleteTasks() {
		Session session = HibernateUtil.openSession();
		Query query = session.createQuery(Task.GET_INCOMPLETE_TASKS);
		List<Task> tasks = query.list();
		List<TaskRO> taskros = new ArrayList<TaskRO>();
		for (Task task : tasks) {
			taskros.add(new TaskRO(task));
		}
		HibernateUtil.closeSession(session);
		return taskros;
	}

	public TaskRO addTask(TaskRO task) {
		Session session = HibernateUtil.openSession();
		Task t = new Task();
		t.setDescription(task.getDescription());
		t.setStatus(task.getStatus());
		session.save(t);
		HibernateUtil.closeSession(session);
		return new TaskRO(t);
	}

	public void updateTask(Long id, TaskRO task) {
		if (id <= 0) {
			return;
		}
		Session session = HibernateUtil.openSession();
		Task t = (Task) session.get(Task.class, id);
		t.setId(id);
		t.setCreated(task.getCreated());
		t.setStatus(task.getStatus());
		t.setDescription(task.getDescription());
		session.update(t);
		HibernateUtil.closeSession(session);
	}

	public TaskRO deleteTask(Long id) {
		Session session = HibernateUtil.openSession();
		Task t = (Task) session.get(Task.class, id);
		session.delete(t);
		HibernateUtil.closeSession(session);
		return new TaskRO(t);
	}
}
