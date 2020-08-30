package com.zomentum.BookingMovies.repository;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AbstractRepository {

	private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();

	private static final Logger logger = Logger.getLogger(AbstractRepository.class);

	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public AbstractRepository() {

	}

	public static Session getSession() {
		Session session = (Session) sessionThread.get();

		if (session == null)
		{
			session = sessionFactory.openSession();
			sessionThread.set(session);
			logger.info("new session has been created.");
		}

		return session;
	}

	public static void begin() {

		getSession().beginTransaction();
		logger.debug("beginning a new Transaction.");

	}

	public static void commit() {
		try {
			getSession().getTransaction().commit();
			logger.debug("Successfully commited to the DB.");
		} catch (HibernateException hibernateException) {
			logger.error("Exception has occurred while commiting to the DB : ", hibernateException);;
		}
	}

	public static void rollback() {
		try {
			getSession().getTransaction().rollback();
			logger.debug("Successfully Rolled back from the Txn.");
		} catch (HibernateException hibernateException) {
			logger.error("Exception has occurred while rolling back from the Txn :", hibernateException);;
		}

		try {
			getSession().close();
			logger.info("session has been closed.");
		} catch (HibernateException hibernateException) {
			logger.error("Exception has occurred while closing the session ", hibernateException);
		}

		sessionThread.set(null);
	}

	public static void close() {
		getSession().close();// closing the session
		logger.info("session has been closed.");
		sessionThread.set(null); // setting current session to null
	}
}
