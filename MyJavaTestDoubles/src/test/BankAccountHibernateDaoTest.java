package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import main.BankAccount;
import main.MyBankAccount;
import main.hibernate.BankAccountHibernateDao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankAccountHibernateDaoTest {

	private static SessionFactory factory;
	private static BankAccountHibernateDao accDao;
	private BankAccount acc1;
	private BankAccount acc2;

	@BeforeClass
	public static void baseSetUp() {
		factory = new Configuration().configure().buildSessionFactory();
		accDao = new BankAccountHibernateDao(factory);
	}

	@Before
	public void setUpTest() throws Exception {
		acc1 = new MyBankAccount(new BigDecimal(10.0), 1L);
		acc2 = new MyBankAccount(new BigDecimal(20.0), 2L);
		accDao.save(acc1);
		accDao.save(acc2);
	}

	@After
	public void tearDown() throws Exception {
		accDao.delete(acc1);
		accDao.delete(acc2);
	}

	@Test
	public void testFindAccountById() {
		BankAccount bankAccount1 = accDao.findAccountById(1L);
		BankAccount bankAccount2 = accDao.findAccountById(2L);
		assertNotNull(bankAccount1);
		assertNotNull(bankAccount2);
		assertEquals(new BigDecimal(10.0), bankAccount1.getBalance());
		assertEquals(new BigDecimal(20.0), bankAccount2.getBalance());
		
		//get not existing one
		BankAccount bankAccount3 = accDao.findAccountById(3L);
		assertNull(bankAccount3);
	}

	@AfterClass
	public static void baseTearDown() {
		factory.close();
	}

}
