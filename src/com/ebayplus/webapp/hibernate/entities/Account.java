package com.ebayplus.webapp.hibernate.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "accounts", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class Account {

	@Id
	@GeneratedValue
	private int accountID;
	@Column
	private String username;
	@Column
	private String password;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<EbayPlusAccount> ebayPlusAccounts = new ArrayList<EbayPlusAccount>();
	
	
	public EbayPlusAccount getEbayPlusAccountByID(int eBayAccountID) {
		EbayPlusAccount eBayPlusAccount = null;
		for (EbayPlusAccount eBayAccount : ebayPlusAccounts) {
			if(eBayAccount.getAccountID() == eBayAccountID) {
				eBayPlusAccount = eBayAccount;
			}
		}
		return eBayPlusAccount;
		
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<EbayPlusAccount> getEbayPlusAccounts() {
		return ebayPlusAccounts;
	}

	public void setEbayPlusAccounts(List<EbayPlusAccount> ebayPlusAccounts) {
		this.ebayPlusAccounts = ebayPlusAccounts;
	}

}
