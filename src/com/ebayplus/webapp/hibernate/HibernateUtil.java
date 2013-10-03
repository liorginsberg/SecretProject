package com.ebayplus.webapp.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.ebayplus.webapp.hibernate.entities.Account;
import com.ebayplus.webapp.hibernate.entities.EbayPlusAccount;
//import org.hibernate.tool.hbm2ddl.SchemaExport;


/* The hiberanteUtil class has a static method that return a singleton instance of SessionFactory */
public class HibernateUtil {
	
	// a singleton implementation of SessionFactory
	private static SessionFactory sessionFactory = null;

	// static method to obtain the same sessionfactory
	static public SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{
			// setting up a new configuration  
			AnnotationConfiguration config = new AnnotationConfiguration();
			
			// adding all the classes to the annotation configuration 
			config.addAnnotatedClass(Account.class);
			config.addAnnotatedClass(EbayPlusAccount.class);
		
			config.configure();
			
			//this part of the code makes it easy to recreate the database with the right schema
			//new SchemaExport(config).create(true, true);
			
			
			sessionFactory = config.buildSessionFactory();
		}
		return sessionFactory;		
	}
}