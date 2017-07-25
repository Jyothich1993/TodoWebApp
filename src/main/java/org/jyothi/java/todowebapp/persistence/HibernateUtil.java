package org.jyothi.java.todowebapp.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionfactory();

	public static Session openSession() {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		return s;
	}

	public static void closeSession(Session s) {
		s.getTransaction().commit();
		s.close();
	}

	private static SessionFactory buildSessionfactory() {
		return new AnnotationConfiguration().configure().buildSessionFactory();

	}
}
