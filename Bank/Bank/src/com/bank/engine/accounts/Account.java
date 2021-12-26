package com.bank.engine.accounts;

import com.bank.engine.utilitys.AccountFileUtilitys;
import com.bank.engine.utilitys.Logging;

public abstract class Account {

	private final String username;
	private final String password;
	private String firstName;
	private String lastName;
	private String state;
	private String city;
	private int zipCode;
	private String street;
	private int houseNumber;

	private final Logging LOGGER = new Logging(Account.class.getName());

	private boolean isLoggedIn = false;

	AccountFileUtilitys fileUtilitys = new AccountFileUtilitys();

	public Account(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	public void clearTransactions() {
		this.LOGGER.success(this + " cleared transactions.");
		this.fileUtilitys.clearTransactions(this);
	}

	public void closeAccount() {
		if (this.fileUtilitys.userLogin(this)) {
			this.LOGGER.success(this + " closed account.");
			this.fileUtilitys.closeAccount(this);
		} else {
		}
	}

	public String fileUtilGetCity() {
		return this.city;
	}

	public String fileUtilGetFirstName() {
		return this.firstName;
	}

	public int fileUtilGetHouseNumber() {
		return this.houseNumber;
	}

	public String fileUtilGetLastName() {
		return this.lastName;
	}

	public String fileUtilGetState() {
		return this.state;
	}

	public String fileUtilGetStreet() {
		return this.street;
	}

	public int fileUtilGetZipcode() {
		return this.zipCode;
	}

	public int getBalance() {
		this.LOGGER.info(this + " balance " + this.fileUtilitys.getBalance(this));
		return this.fileUtilitys.getBalance(this);
	}

	public int getBankID() {
		if (this.isLoggedIn) {
			this.LOGGER.info(this + " BankID " + this.fileUtilitys.getBankID(this));
			return this.fileUtilitys.getBankID(this);
		}
		this.LOGGER.warn("Not logged in.");
		return 0;
	}

	public String getCity() {
		this.LOGGER.info(this + " city " + this.fileUtilitys.getCity(this));
		return this.fileUtilitys.getCity(this);
	}

	public String getFirstName() {
		return this.fileUtilitys.getFirstName(this);
	}

	public String getHouseNumber() {
		this.LOGGER.info(this + " housenumber " + this.fileUtilitys.getHouseNumber(this));
		return this.fileUtilitys.getHouseNumber(this);
	}

	public String getLastName() {
		this.LOGGER.info(this + " last name " + this.fileUtilitys.getLastName(this));
		return this.fileUtilitys.getLastName(this);
	}

	public String getPassword() {
		// LOGGER.info(this + " password " + this.password);
		return this.password;
	}

	public String getPastTransactions() {
		this.LOGGER.info(this + " Past transactions: " + this.fileUtilitys.getTransaction(this));
		return this.fileUtilitys.getTransaction(this);
	}

	public String getState() {
		this.LOGGER.info(this + " state " + this.fileUtilitys.getState(this));
		return this.fileUtilitys.getState(this);
	}

	public String getStreet() {
		this.LOGGER.info(this + " street " + this.fileUtilitys.getStreet(this));
		return this.fileUtilitys.getStreet(this);
	}

	public String getUsername() {
		return this.username;
	}

	public String getZip() {
		this.LOGGER.info(this + " zipcode " + this.fileUtilitys.getZipcode(this));
		return this.fileUtilitys.getZipcode(this);
	}

	public boolean login() {
		if (this.fileUtilitys.userLogin(this)) {
			this.LOGGER.success(this + " succesfully logged in.");
			this.isLoggedIn = true;
			return true;
		}
		this.LOGGER.warn(this + " failed to login.");
		return false;
	}

	public void openAccount() {
		if (!this.fileUtilitys.doesUsernameExist(this)) {
			this.LOGGER.success(this + " opened account.");
			this.fileUtilitys.createNewUser(this);
		}
	}

	public void setAddress(final String state, final String city, final int zipCode, final String street,
			final int houseNumber) {
		this.LOGGER.success("Address Set: " + state + " " + city + " " + zipCode + " " + street + " " + houseNumber);
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public void setBalance(final int amount, final String reason) {
		if ((amount > 0) || ((amount < 0) && (this.getBalance() < amount))) {
			this.fileUtilitys.setTransaction(this, amount, reason);
		}
	}

	public void setName(final String firstName, final String lastName) {
		this.LOGGER.success("Name Set: " + firstName + " " + lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setTransaction(int amount, final String reason) {
		// Bank transaction
		if (amount > 0) {
			this.LOGGER.success(this + " has deposited $" + amount);
			this.fileUtilitys.setTransaction(this, this.getBalance() + amount, reason);
		} else if (amount < 0) {
			amount *= -1;
			if (this.getBalance() > amount) {
				this.LOGGER.success(this + "has withdrew $" + amount);
				this.fileUtilitys.setTransaction(this, this.getBalance() - amount, reason);
			} else {
				this.LOGGER.warn(this + " does not have sufficient funds.");
			}
		}
	}

	public void setTransaction(final int amount, final String reason, final Account account) {
		if (this.isLoggedIn) {
			// User to user transaction
			if (amount > 0) {
				this.LOGGER.success(this + " has sent $" + amount + " to " + account);
				this.fileUtilitys.setTransaction(this, this.getBalance() - amount, reason, account);
				this.fileUtilitys.setTransaction(account, account.getBalance() + amount, reason, this);

			} else if (amount < 0) {
				if (this.getBalance() < amount) {
					this.LOGGER.success(this + "has sent $" + amount + "to " + account);
					this.fileUtilitys.setTransaction(this, this.getBalance() - amount, reason, account);
				} else {
					this.LOGGER.warn(account + " does not have sufficient funds for this transaction.");
				}
			}
		}
	}

	@Override
	public String toString() {
		return this.username;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}
}
