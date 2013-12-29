package main;

import java.math.BigDecimal;

public interface BankAccount {
	public long getAccountId();
	public void setAccountId(long accountId);
	public void deposit(BigDecimal amount);
	public void withdraw(BigDecimal amount);
	public BigDecimal getBalance();
	public void setBalance(BigDecimal balance);
}
