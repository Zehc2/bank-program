package com.bank.engine.banking;

import com.bank.engine.accounts.Account;
import com.bank.engine.utilitys.BankFileUtilitys;
import com.bank.engine.utilitys.Logging;

public class Bank {

	BankFileUtilitys bankFileUtilitys = new BankFileUtilitys();

	private final Logging LOGGER = new Logging(Bank.class.getName());

	public Bank() {
	}

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

	public void createBank() {
		this.LOGGER.success("New bank created");
		this.bankFileUtilitys.createBank();
	}

	public int getCurrentBankBalance() {
		return this.bankFileUtilitys.getBankTotal();
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
