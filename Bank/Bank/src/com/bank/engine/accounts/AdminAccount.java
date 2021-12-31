package com.bank.engine.accounts;

public class AdminAccount extends Account {

	Bank bank = new Bank();

	public AdminAccount(final String username, final String password) {
		super(username, password);
	}

	@Override
	public AccountType dataBaseGetAccountType() {
		return AccountType.ADMIN;
	}

	public void getOtherAccount(final String username) {

	}

}
