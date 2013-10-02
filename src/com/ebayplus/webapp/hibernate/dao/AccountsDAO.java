package com.ebayplus.webapp.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.ebayplus.webapp.hibernate.HibernateUtil;
import com.ebayplus.webapp.hibernate.entities.Account;
import com.ebayplus.webapp.hibernate.entities.EbayPlusAccount;

public class AccountsDAO {

	private static AccountsDAO instance = null;
	
	private AccountsDAO() { }
	
	// implementing the single tone design for the DAO Object 
	public static AccountsDAO getDAO()
	{
		if(instance==null)
		{
			instance = new AccountsDAO();
		}
		return instance;
	}


	public int addAccount(Account account) {
		int id = -1;
		try{
			Session session = HibernateUtil.getSassionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();
			id = account.getAccountID();
		}catch(ConstraintViolationException e) {
			
		}
		return id;
	}
	
	public Account getAccountByID(int accountID) {
		 Session session = null;
	     Account account = null;
         try {
            session = HibernateUtil.getSassionFactory().openSession();
            account =  (Account)session.get(Account.class,
                    accountID);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
	}

	
	public void deleteAccount(Account account) {
//		Session session = HibernateUtil.getSassionFactory().getCurrentSession();
//		session.beginTransaction();
//		session.delete(account);
//		session.getTransaction().commit();
//		int id = account.getAccount_id();
//		return id;
	}
	
}