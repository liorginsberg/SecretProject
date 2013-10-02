package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ebayplus.webapp.hibernate.dao.AccountsDAO;
import com.ebayplus.webapp.hibernate.entities.Account;
import com.ebayplus.webapp.hibernate.entities.EbayPlusAccount;

public class DAOTests {

	private static AccountsDAO accountDao;
	private static final String token = "AgAAAA**AQAAAA**aAAAAA**OZcZUg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCpiAqQSdj6x9nY+seQ**n1ECAA**AAMAAA**c+YkTRAUnTj8LVGn/9sIsRiQyjMih2fT7P6QPe8O5l8jlZBeCCyfBw1RXWGy0wzCJunv3m9dDK8z2vxuks2xBYZ5w6UTXhs+DbRU9cXmERiZNfEP6mSJ1eX1eCYqcixGU80h92rKvGT3gfH/RVSua47pwe35cBQXXZF4QwaKY8PeLlu9Ib1bMtJMKIXrsC/U9WOAptg+U3V/6KmKy2VEX+j8RdTDNVXzPoYXFLZ+A/pIDs7EwCwBpdSRbiwx7c9JRGJNSnbCDfYt8Uogu+YHkGGrTWoJPaq//wDKuGzIyYEm6d+95T8xf3xnGFTaHvaAxRkEMO0jJxgZ+G1Q1X5U++6rf1QMZ9YUCLg3b8n/Wfd3I5DTMM8BWuGcRQK/J8IZdEuv9gnbvKBXq+WeLkARBe/Yflid1D4ttlbrrpSWEomu9rsweYtFUvTKvCwUSJteXa8Djixl2bb7G8upSjxXbCStVEdsxOdo1UZ9GC8HgyqYXS6nfPT5RSdehqZI1QaLVxKhi8nlGFPfnf7MuM+CDK7iNjo/47U26VFB59a4H5JGge+RoIavxrgWGXql09xTWCIhMdWnUgMtrgPRh5mwwlYUx3ie3cI6+6LmdfiauffylyMVW6ZvNACSZokHYsLRmAz5ZRwiaLYtXH28Q+IzGWgve6fpNqiWXjwNSQs9iOUIVxkh6w0J/d6gpJJY+5FpSdk48zvPUOvYJYINiqJopp/dNskVxGWVMZcjqFbWsB9YSd4WJoqbUcaAnWYcQ99m";
	
	@BeforeClass
	public static void setup() {
		accountDao = AccountsDAO.getDAO();
	}
	
	
	/**
	 * 1. add account to db.
	 * 2. verify that valid id returned.
	 * 3. retrieve account from db.
	 */
	@Test
	public void addAccount() {
		
		Account account = new Account();
		account.setUsername("test");
		account.setPassword("testpass");
		
		int accountID = accountDao.addAccount(account);
		accountDao.deleteAccount(accountID);
	}
	
	@Test
	public void addAccountWithEbayAccount() {
		Account account = new Account();
		account.setUsername("test");
		account.setPassword("testpass");
		
		EbayPlusAccount ebayPlusAccount = new EbayPlusAccount();
		ebayPlusAccount.setAccountAlias("liorAlias");
		ebayPlusAccount.setAccountColor("red");
		ebayPlusAccount.setAccountAdmin("zivGin@gmail.com");
		ebayPlusAccount.setAccountName("my name");
		ebayPlusAccount.setAccountSession_exp(new Date());
		
		account.getEbayPlusAccounts().add(ebayPlusAccount);
		
		int accountID = accountDao.addAccount(account);
		accountDao.deleteAccount(accountID);
	}
	
	@Test
	public void updateAccount() {
		Account account = new Account();
		account.setUsername("test");
		account.setPassword("testpass");
		int accountID = accountDao.addAccount(account);
		
		account.setPassword("newPass");
		accountDao.updateAccount(account);
		
		accountDao.deleteAccount(accountID);
	}
	
	@Test
	public void getAccountByID() {
		Account account = new Account();
		account.setUsername("test");
		account.setPassword("testpass");
		
		
		int accountID = accountDao.addAccount(account);

		Account fromDB = accountDao.getAccountById(accountID);
		
		System.out.println(fromDB.getAccountID() + ") " + fromDB.getUsername() + ", " + fromDB.getPassword());
		
		accountDao.deleteAccount(accountID);
	}
	
	@Test
	public void getAllAccounts () {
		List<Account> accounts = null;
		
		Account account1 = new Account();
		account1.setUsername("test1");
		account1.setPassword("testpass");
		accountDao.addAccount(account1);
		
		Account account2 = new Account();
		account2.setUsername("test2");
		account2.setPassword("testpass");
		accountDao.addAccount(account2);
		
		Account account3 = new Account();
		account3.setUsername("test3");
		account3.setPassword("testpass");
		accountDao.addAccount(account3);
		
		accounts = accountDao.getAllAccounts();
		
		for(Account account: accounts) {
			System.out.println(account.getAccountID() + ")" + account.getUsername() + ", " + account.getPassword());
		}
		
		for(Account account: accounts) {
			accountDao.deleteAccount(account.getAccountID());
		}
	}
	
	@Test
	public void updaeteEbayPlusAccount() {
		Account account = new Account();
		account.setUsername("test");
		account.setPassword("testpass");
		
		EbayPlusAccount ebayPlusAccount = new EbayPlusAccount();
		ebayPlusAccount.setAccountAlias("liorAlias");
		ebayPlusAccount.setAccountColor("red");
		ebayPlusAccount.setAccountAdmin("zivGin@gmail.com");
		ebayPlusAccount.setAccountName("my name");
		ebayPlusAccount.setAccountSession_exp(new Date());
		
		account.getEbayPlusAccounts().add(ebayPlusAccount);
		
		int accountID = accountDao.addAccount(account);
		
		Account accountFromDB = accountDao.getAccountById(accountID);
		EbayPlusAccount ebayPlusAccountfromDB = accountFromDB.getEbayPlusAccounts().get(0);
		ebayPlusAccountfromDB.setAccountColor("Green");
		accountDao.updateAccount(accountFromDB);
		
		accountDao.deleteAccount(accountID);
		
	}
}