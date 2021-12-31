package com.bank.engine.utilitys;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.bank.engine.accounts.Account;
import com.bank.engine.accounts.AccountType;
import com.bank.engine.accounts.Bank;

public class DataBaseUtilitys {

	Bank bank = new Bank();

	Logging LOGGER = new Logging(DataBaseUtilitys.class.getName());

	private final String name = "root";
	private final String password = "#ski4life";
	private final String url = "jdbc:mysql://localhost:3306/BankProgram";

	public void closeAccount(final Account account) {
		try {
			final var query = "xv  ";
			final var query1 = "delete from accountregistration where username = ?;";

			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			var st = con.prepareStatement(query);
			st.setString(1, this.bank.getUsername(account));
			st.executeUpdate();
			st = con.prepareStatement(query1);
			st.setString(1, this.bank.getUsername(account));
			st.executeUpdate();
			st.executeUpdate();
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
	}

	public void createNewUser(final Account account) {
		this.setAccountInformation(account);
		this.setAccountRegistration(account);
	}

	public boolean doesUsernameExist(final String username) {
		try {
			final var query = "select * from accountregistration";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(1).equals(username)) {
					return true;
				}
			}
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return false;
	}
	
	public int getUniqueID() {
		int returnNum = 0;
		try {
			final var query = "select * from accountregistration";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);	
		
			while (rs.next()) {
				returnNum++;
			}
			st.close();
			con.close();	
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return returnNum;
	}


	public AccountType getAccountType(final Account account) {
		try {
			final var query = "select * from accountregistration;";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(1).equals(this.bank.getUsername(account))) {
					String accountTypeStr = rs.getString(3);									
					if(accountTypeStr.equals(AccountType.INVESTMENT.toString())) {
						return AccountType.INVESTMENT;
					} else if(accountTypeStr.equals(AccountType.SAVINGS.toString())) {
						return AccountType.SAVINGS;
					} else if(accountTypeStr.equals(AccountType.CHECKING.toString())) {
						return AccountType.CHECKING;
					}
				}
			}
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return null;
		
	}

	public int getBalance(final Account account) {
		try {
			final var query = "select * from accountinformation";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getString(9).equals(this.bank.getUsername(account))) {
					return rs.getInt(8);
				}
			}
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}

		return 0;

	}

	public int getBankID(final Account account) {
		try {
			final var query = "select * from accountregistration";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(1).equals(this.bank.getUsername(account))) {
					return rs.getInt(4);
				}
			}
			st.close();
			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return 0;
	}

	public String getCity(final Account account) {
		try {
			final var query = "select * from accountinformation";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(9).equals(this.bank.getUsername(account))) {
					return rs.getString(5);
				}
			}
			st.close();
			con.close();

		} catch (SQLException | ClassNotFoundException e) {

		}
		return null;
	}

	public String getFirstName(final Account account) {
		try {
			final var query = "select * from accountinformation";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(9).equals(this.bank.getUsername(account))) {
					return rs.getString(1);
				}
				continue;
			}
			st.close();
			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return null;
	}

	public int getHouseNumber(final Account account) {
		try {
			final var query = "select * from accountinformation";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(9).equals(this.bank.getUsername(account))) {
					return rs.getInt(7);
				}
			}
			st.close();
			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return 0;
	}

	public String getLastName(final Account account) {
		try {
			final var query = "select * from accountinformation";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(9).equals(this.bank.getUsername(account))) {
					return rs.getString(2);
				}
			}
			st.close();
			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return null;
	}

	public String getState(final Account account) {
		try {
			final var query = "select * from accountinformation";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(9).equals(this.bank.getUsername(account))) {
					return rs.getString(3);
				}
			}
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return null;
	}

	public String getStreet(final Account account) {
		try {
			final var query = "select * from accountinformation";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(9).equals(this.bank.getUsername(account))) {
					return rs.getString(6);
				}
			}
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return null;
	}

	public String getUserPassword(final Account account) throws ClassNotFoundException, SQLException {
		final var query = "select * from accountregistration";
		Class.forName("com.mysql.cj.jdbc.Driver");
		final var con = DriverManager.getConnection(this.url, this.name, this.password);
		final var st = con.createStatement();
		final var rs = st.executeQuery(query);
		while (rs.next()) {
			if (rs.getString(1).equals(this.bank.getUsername(account))) {
				return rs.getString(2);
			}
		}
		st.close();
		con.close();
		return null;
	}

	public Account getUserWithUsername(final String username) {
		try {
			final var query = "select password from accountregistration where username = '?'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.prepareStatement(query);
			st.executeUpdate();
			final var password = st.toString();
			st.close();
			con.close();
			return this.bank.createNewTempAccount(username, password);
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return null;
	}

	public int getZip(final Account account) {
		try {
			final var query = "select * from accountinformation";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(9).equals(this.bank.getUsername(account))) {
					return rs.getInt(4);
				}
			}
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return 0;
	}

	private void setAccountInformation(final Account account) {
		try {
			final var query = "insert into accountinformation values (?,?,?,?,?,?,?,?,?);";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.prepareStatement(query);
			st.setString(1, account.dataBaseGetFirstName());
			st.setString(2, account.dataBaseGetLastName());
			st.setString(3, account.dataBaseGetState());
			st.setInt(4, account.dataBaseGetZipCode());
			st.setString(5, account.dataBaseGetCity());
			st.setString(6, account.dataBaseGetStreet());
			st.setInt(7, account.dataBaseGetHouseNumber());
			st.setInt(8, 0);
			st.setString(9, this.bank.getUsername(account));
			st.executeUpdate();
			st.close();
			con.close();
		} catch (final Exception e) {
			this.LOGGER.severe("setAccountInformation " + e);
		}
	}

	private void setAccountRegistration(final Account account) {
		try {
			final var query = "insert into accountregistration values (?,?,?,?);";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.prepareStatement(query);
			st.setString(1, this.bank.getUsername(account));
			st.setString(2, this.bank.getPassword(account));
			st.setString(3, this.bank.dataBaseGetAccountType(account).toString());
			st.setInt(4, account.dataBaseGetUserID());
			st.executeUpdate();
			st.close();
			con.close();
		} catch (final Exception e) {
			this.LOGGER.severe("setAccountRegistration " + e);
		}
	}

	public void setTransaction(final Account account, final int amount) {
		try {
			final var query = "update accountinformation set balance = ? where username = ?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.prepareStatement(query);
			st.setInt(1, amount);
			st.setString(2, this.bank.getUsername(account));
			st.executeUpdate();
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
	}

	public boolean userLogin(final Account account) {
		try {
			final var query = "select * from accountregistration";
			Class.forName("com.mysql.cj.jdbc.Driver");
			final var con = DriverManager.getConnection(this.url, this.name, this.password);
			final var st = con.createStatement();
			final var rs = st.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(1).equals(this.bank.getUsername(account))
						&& rs.getString(2).equals(this.bank.getPassword(account))) {
					return true;
				}
			}
			st.close();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			this.LOGGER.warn(e);
		}
		return false;
	}
}
