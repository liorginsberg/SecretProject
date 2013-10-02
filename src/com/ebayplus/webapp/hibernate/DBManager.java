package com.ebayplus.webapp.hibernate;

import org.hibernate.Session;

public class DBManager {

	public static void truncateTable(String tableName) {
		Session session = HibernateUtil.getSassionFactory().getCurrentSession();
		session.beginTransaction();
		session.createSQLQuery("truncate table " + tableName).executeUpdate();
		session.getTransaction().commit();
	}
}
