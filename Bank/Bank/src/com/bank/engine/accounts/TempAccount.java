package com.bank.engine.accounts;

public class TempAccount extends Account {

	protected TempAccount(String username, String password) {
		super(username, password);
	}

	@Override
	public AccountType dataBaseGetAccountType() {
		return null;
	}

}
