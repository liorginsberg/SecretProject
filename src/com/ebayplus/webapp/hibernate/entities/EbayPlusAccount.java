package com.ebayplus.webapp.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ebayplus_accounts", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"accountName", "accountAlias" }) })
public class EbayPlusAccount {

	@Id
	@GeneratedValue
	private int accountID;
	@Column
	private String accountName;
	@Column
	private String accountAdmin;
	@Column
	private String accountAlias;
	
	private String accountColor;
	@Column
	private String accountSession;
	@Column
	private Date accountSession_exp;
	@Column(length = 65535,columnDefinition="Text")
	private String accountToken;
	@Column
	private Date accountToken_exp;
	@Column
	@Enumerated(EnumType.STRING)
	private AccountStatusType accountStatus = AccountStatusType.WAITING_FOR_ACTIVATION;
	
	//@ManyToOne - for invese relationship
	//private Account account;


	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountAdmin() {
		return accountAdmin;
	}

	public void setAccountAdmin(String accountAdmin) {
		this.accountAdmin = accountAdmin;
	}

	public String getAccountAlias() {
		return accountAlias;
	}

	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}

	public String getAccountColor() {
		return accountColor;
	}

	public void setAccountColor(String accountColor) {
		this.accountColor = accountColor;
	}

	public String getAccountSession() {
		return accountSession;
	}

	public void setAccountSession(String accountSession) {
		this.accountSession = accountSession;
	}

	public Date getAccountSession_exp() {
		return accountSession_exp;
	}

	public void setAccountSession_exp(Date accountSession_exp) {
		this.accountSession_exp = accountSession_exp;
	}

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public Date getAccountToken_exp() {
		return accountToken_exp;
	}

	public void setAccountToken_exp(Date accountToken_exp) {
		this.accountToken_exp = accountToken_exp;
	}

	public AccountStatusType getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatusType accountStatus) {
		this.accountStatus = accountStatus;
	}

	
	//In case we want Inverse relationship
//	public Account getAccount() {
//		return account;
//	}
//	
//	public void setAccount(Account account) {
//		this.account = account;
//	}
}