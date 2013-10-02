package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ebayplus.webapp.hibernate.DBManager;
import com.ebayplus.webapp.hibernate.dao.AccountsDAO;
import com.ebayplus.webapp.hibernate.entities.Account;

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
		
		assertNotSame("Got -1 after adding account", -1, accountID);
		
		Account accountFromDb = null;
		accountFromDb = accountDao.getAccountByID(accountID);
		
		assertNotNull("could not retrive account with accountID = " + accountID, accountFromDb);
			
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void addAccountWithTheSameUserName() {
		
		Account account1 = new Account();
		account1.setUsername("test");
		account1.setPassword("testpass");
		
		Account account2 = new Account();
		account2.setUsername("test");
		account2.setPassword("testpass");
		
		accountDao.addAccount(account1);		
		accountDao.addAccount(account2);
		
		
	}
	
}

/**
 * EbayPlusAccount ebayPlusAccount = new EbayPlusAccount();
		ebayPlusAccount.setAccountAdmin("lior4@gmail.com");
		ebayPlusAccount.setAccountAlias("lioralias4");
		ebayPlusAccount.setAccountColor("red4");
		ebayPlusAccount1.setAccountToken(token);
		account1.getEbayPlusAccounts().add(ebayPlusAccount1);
		
		EbayPlusAccount ebayPlusAccount2 = new EbayPlusAccount();
		ebayPlusAccount2.setAccountAdmin("lior5@gmail.com");
		ebayPlusAccount2.setAccountAlias("lioralias5");
		ebayPlusAccount2.setAccountColor("red");
		ebayPlusAccount2.setAccountToken(token);
		account1.getEbayPlusAccounts().add(ebayPlusAccount2);
		
		EbayPlusAccount ebayPlusAccount3 = new EbayPlusAccount();
		ebayPlusAccount3.setAccountAdmin("lior6@gmail.com");
		ebayPlusAccount3.setAccountAlias("lioralias6");
		ebayPlusAccount3.setAccountColor("red6");
		ebayPlusAccount3.setAccountToken(token);
		account1.getEbayPlusAccounts().add(ebayPlusAccount3);
		
		
		accountDao.addAccount(account1);
		
		Account account = accountDao.getAccountByID(1);
		System.out.println(account.getUsername());
		
		System.out.println("before insert" + token.length());
		List<EbayPlusAccount> accounts = account.getEbayPlusAccounts();
		if(accounts != null) {
			for(EbayPlusAccount ebayPlusAccount : accounts) {
				System.out.println("size after getAccount" + ebayPlusAccount.getAccountToken().length());
			}
		} else {
			System.out.println("need init");
		}
 */
