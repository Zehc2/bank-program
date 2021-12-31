package com.bank.engine.accounts;

public class InvestmentAccount extends Account {

	public InvestmentAccount(String username, String password) {
		super(username, password);
	}

	@Override
	public AccountType dataBaseGetAccountType() {
		return AccountType.INVESTMENT;
	}
}
