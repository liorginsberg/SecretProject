package com.ebayplus.webapp.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ebayplus.webapp.hibernate.HibernateUtil;
import com.ebayplus.webapp.hibernate.entities.Account;

public class AccountsDAO {

	private static AccountsDAO instance = null;

	private AccountsDAO() {
	}

	// implementing the single tone design for the DAO Object
	public static AccountsDAO getDAO() {
		if (instance == null) {
			instance = new AccountsDAO();
		}
		return instance;
	}

	// public int addAccount(Account account) {
	// int id = -1;
	// try{
	// Session session = HibernateUtil.getSassionFactory().getCurrentSession();
	// session.beginTransaction();
	// session.save(account);
	// session.getTransaction().commit();
	// id = account.getAccountID();
	// }catch(ConstraintViolationException e) {
	//
	// }
	// return id;
	// }
	//
	// public Account getAccountByID(int accountID) {
	// Session session = null;
	// Account account = null;
	// try {
	// session = HibernateUtil.getSassionFactory().openSession();
	// account = (Account)session.get(Account.class,
	// accountID);
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (session != null && session.isOpen()) {
	// session.close();
	// }
	// }
	// return account;
	// }

	public int addAccount(Account account) {

		int accountID = -1;
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();
			accountID = account.getAccountID();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return accountID;
	}

	public void deleteAccount(int accountID) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			Account account = (Account) session.load(Account.class,
					new Integer(accountID));
			session.delete(account);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void updateAccount(Account account) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(account);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			accounts = session.createQuery("from Account").list();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return accounts;
	}

	public Account getAccountById(int accountID) {
		Account account = null;
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			String queryString = "from Account where accountID = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", accountID);
			account = (Account) query.uniqueResult();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return account;
	}

}
