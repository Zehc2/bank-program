package com.bank.engine.accounts;

import com.bank.engine.utilitys.DataBaseUtilitys;
import com.bank.engine.utilitys.Logging;

public class Bank {

	private final Logging LOGGER = new Logging(Bank.class.getName());

	public void cashDeposit(final int amount, final String reason, final Account account) {
		if (amount >= 10) {
			this.LOGGER.success(this + " deposit " + amount + " " + reason + " " + account);
			account.setTransaction(amount, reason);
		} else {
			this.LOGGER.warn("Can not deposit under 10$");
		}
	}

	public boolean cashWithdrawal(int amount, final String reason, final Account account) {
		if (amount > 0) {
			if (account.getBalance() > amount) {
				this.LOGGER.success(this + " cash withdrawal " + amount + " " + reason + " " + account);
				amount *= -1;
				account.setTransaction(amount, reason);
				return true;
			}
		} else {
			this.LOGGER.warn("Can not withdraw this amount of money");
		}
		return false;
	}

	protected void clearTransactions(final Account account) {
		account.clearTransactions();
	}

	public void closeAccount(final Account account) {
		account.closeAccount();
	}

	public Account createNewCheckingAccount(final String username, final String password) {
		return new CheckingAccount(username, password);
	}

	public Account createNewInvestmentAccount(final String username, final String password) {
		return new InvestmentAccount(username, password);
	}

	public Account createNewSavingsAccount(final String username, final String password) {
		return new SavingsAccount(username, password);
	}

	public Account createNewTempAccount(final String username, final String password) {
		return new TempAccount(username, password);
	}

	public AccountType dataBaseGetAccountType(final Account account) {
		return account.dataBaseGetAccountType();
	}

	public boolean doesUserExist(final String username) {
		final DataBaseUtilitys dataBase = new DataBaseUtilitys();
		if (dataBase.doesUsernameExist(username)) {
			return true;
		}
		return false;
	}

	public AccountType getAccountType(final Account tempAccount) {
		return tempAccount.getAccountType();
	}

	public int getBalance(final Account account) {
		return account.getBalance();
	}

	public String getCity(final Account account) {
		return account.getCity();
	}

	public String getFirstName(final Account account) {
		return account.getFirstName();
	}

	public int getHouseNumber(final Account account) {
		return account.getHouseNumber();
	}

	public int getID(final Account account) {
		return account.getBankID();
	}

	public String getLastName(final Account account) {
		return account.getLastName();
	}

	public String getPassword(final Account account) {
		return account.getPassword();
	}

	public String getState(final Account account) {
		return account.getState();
	}

	public String getStreet(final Account account) {
		return account.getStreet();
	}

	public String getUsername(final Account account) {
		return account.getUsername();
	}

	public int getZIP(final Account account) {
		return account.getZip();
	}

	public boolean login(final Account account) {
		return account.login();
	}

	public void logout(final Account account) {
		account.logout();
	}

	public void setUpAccount(final Account account, final String firstName, final String lastName, final String state,
			final String city, final String zipcode, final String street, final String houseNumber) {
		account.setName(firstName, lastName);
		account.setAddress(state, city, Integer.parseInt(zipcode), street, Integer.parseInt(houseNumber));
		account.openAccount();

		account.login();
	}

	public void testAccount(final Account account) {
		account.getUsername();
		account.getFirstName();
		account.getLastName();
		account.getBankID();
		account.getHouseNumber();
		account.getCity();
		account.getState();
		account.getZip();
		account.toString();
	}

	@Override
	public String toString() {
		return "bank";
	}

	public boolean transfer(final int amount, final Account transfererAccount, final Account transfereeAccount) {
		try {
			if (transfererAccount.getBalance() >= amount) {
				transfererAccount.setTransaction(amount, null, transfereeAccount);
				return true;
			}
			this.LOGGER.warn(transfererAccount + " does not have sufficcient funds.");
			return false;

		} catch (final Error e) {
			return false;
		}
	}
}
