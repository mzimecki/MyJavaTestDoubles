package main.hibernate;

import java.util.List;

import main.BankAccount;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class BankAccountHibernateDao {

	private static SessionFactory factory;
	private static Session session;

	public BankAccountHibernateDao(SessionFactory fact) {
		BankAccountHibernateDao.factory = fact;
		BankAccountHibernateDao.session = getSession();
	}

	public BankAccount findAccountById(long ticketId) {
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.add(Restrictions.eq("accountId", ticketId));
		List<BankAccount> bankAccounts = criteria.list();
		if (!bankAccounts.isEmpty()) {
			return bankAccounts.get(0);
		} else {
			return null;
		}
	}

	public void save(BankAccount account) {
		session.save(account);
		session.flush();
	}

	public void delete(BankAccount account) {
		session.delete(account);
		session.flush();
	}

	private static synchronized Session getSession() {
		return factory.openSession();
	}
}
