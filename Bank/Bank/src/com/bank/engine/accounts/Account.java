package com.bank.engine.accounts;

import com.bank.engine.utilitys.DataBaseUtilitys;
import com.bank.engine.utilitys.Logging;

public abstract class Account {

	private String city;
	DataBaseUtilitys dataBase = new DataBaseUtilitys();
	private String firstName;
	private int houseNumber;
	private boolean isLoggedIn = false;
	private String lastName;
	private final Logging LOGGER = new Logging(Account.class.getName());
	private final String password;
	private String state;
	private String street;

	private int userID;

	private final String username;

	private int zipCode;

	protected Account(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	protected void clearTransactions() {
		this.LOGGER.success(this + " cleared transactions.");
		// this.fileUtilitys.clearTransactions(this);
	}

	protected void closeAccount() {
		if (this.isLoggedIn) {
			this.LOGGER.success(this + " closed account.");
			this.dataBase.closeAccount(this);
		} else {
		}
	}

	public abstract AccountType dataBaseGetAccountType();

	public String dataBaseGetCity() {
		return this.city;
	}

	public String dataBaseGetFirstName() {
		return this.firstName;
	}

	public int dataBaseGetHouseNumber() {
		return this.houseNumber;
	}

	public String dataBaseGetLastName() {
		return this.lastName;
	}

	public String dataBaseGetState() {
		return this.state;
	}

	public String dataBaseGetStreet() {
		return this.street;
	}

	public int dataBaseGetUserID() {
		return this.userID;
	}

	public int dataBaseGetZipCode() {
		return this.zipCode;
	}

	protected AccountType getAccountType() {
		this.LOGGER.info(this + " account type: " + this.dataBase.getAccountType(this));
		return this.dataBase.getAccountType(this);
	}

	protected int getBalance() {
		this.LOGGER.info(this + " balance " + this.dataBase.getBalance(this));
		return this.dataBase.getBalance(this);
	}

	protected int getBankID() {
		if (this.isLoggedIn) {
			this.LOGGER.info(this + " BankID " + this.dataBase.getBankID(this));
			return this.dataBase.getBankID(this);
		}
		this.LOGGER.warn("Not logged in.");
		return 0;
	}

	protected String getCity() {
		this.LOGGER.info(this + " city " + this.dataBase.getCity(this));
		return this.dataBase.getCity(this);
	}

	protected String getFirstName() {
		return this.dataBase.getFirstName(this);
	}

	protected int getHouseNumber() {
		this.LOGGER.info(this + " housenumber " + this.dataBase.getHouseNumber(this));
		return this.dataBase.getHouseNumber(this);
	}

	protected String getLastName() {
		this.LOGGER.info(this + " last name " + this.dataBase.getLastName(this));
		return this.dataBase.getLastName(this);
	}

	protected String getPassword() {
		return this.password;
	}

	protected String getPastTransactions() {
		return null;
	}

	protected String getState() {
		this.LOGGER.info(this + " state " + this.dataBase.getState(this));
		return this.dataBase.getState(this);
	}

	protected String getStreet() {
		this.LOGGER.info(this + " street " + this.dataBase.getStreet(this));
		return this.dataBase.getStreet(this);
	}

	protected String getUsername() {
		return this.username;
	}

	protected int getZip() {
		this.LOGGER.info(this + " zipcode " + this.dataBase.getZip(this));
		return this.dataBase.getZip(this);
	}

	protected boolean isLoggedIn() {
		return this.isLoggedIn;
	}

	protected boolean login() {
		if (this.dataBase.userLogin(this)) {
			this.LOGGER.success(this + " succesfully logged in.");
			this.isLoggedIn = true;
			return true;
		}
		this.LOGGER.warn(this + " failed to login.");
		return false;
	}

	protected void logout() {
		this.isLoggedIn = false;
	}

	protected void openAccount() {
		if (!this.dataBase.doesUsernameExist(this.getUsername())) {
			this.userID = this.dataBase.getUniqueID();
			this.LOGGER.success(this + " opened account.");
			this.dataBase.createNewUser(this);
		}
	}

	protected void setAddress(final String state, final String city, final int zipCode, final String street,
			final int houseNumber) {
		this.LOGGER.success("Address Set: " + state + " " + city + " " + zipCode + " " + street + " " + houseNumber);
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	protected void setName(final String firstName, final String lastName) {
		this.LOGGER.success("Name Set: " + firstName + " " + lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	protected void setTransaction(int amount, final String reason) {
		// Bank transaction
		if (amount > 0) {
			this.LOGGER.success(this + " has deposited $" + amount);
			this.dataBase.setTransaction(this, this.getBalance() + amount);
		} else if (amount < 0) {
			amount *= -1;
			if (this.getBalance() > amount) {
				this.LOGGER.success(this + "has withdrew $" + amount);
				this.dataBase.setTransaction(this, this.getBalance() - amount);
			} else {
				this.LOGGER.warn(this + " does not have sufficient funds.");
			}
		}
	}

	protected void setTransaction(final int amount, final String reason, final Account account) {
		if (this.isLoggedIn) {
			// User to user transaction
			if (amount > 0) {
				this.LOGGER.success(this + " has sent $" + amount + " to " + account);
				this.dataBase.setTransaction(this, this.getBalance() - amount);
				this.dataBase.setTransaction(account, account.getBalance() + amount);

			} else if (amount < 0) {
				if (this.getBalance() < amount) {
					this.LOGGER.success(this + "has sent $" + amount + "to " + account);
					this.dataBase.setTransaction(this, this.getBalance() - amount);
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
}
