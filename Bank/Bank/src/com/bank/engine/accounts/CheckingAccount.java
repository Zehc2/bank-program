package com.bank.engine.accounts;

public class CheckingAccount extends Account {
	
	public CheckingAccount(String username, String password) {
		super(username, password);
	}

	@Override
	public AccountType dataBaseGetAccountType() {
		return AccountType.CHECKING;
	}
}
