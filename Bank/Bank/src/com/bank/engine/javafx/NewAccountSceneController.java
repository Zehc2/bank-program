package com.bank.engine.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.bank.engine.accounts.Account;
import com.bank.engine.accounts.AccountType;
import com.bank.engine.accounts.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAccountSceneController extends Controller {

	private final AccountType[] accountType = { AccountType.CHECKING, AccountType.INVESTMENT, AccountType.SAVINGS };

	@FXML
	ChoiceBox<AccountType> accountTypeChoiceBox;
	Bank bank = new Bank();
	@FXML
	TextField cityTextField;
	@FXML
	TextField firstNameTextField;
	@FXML
	TextField houseNumberTextField;
	@FXML
	Label invalidInput;
	@FXML
	TextField lastNameTextField;
	@FXML
	TextField passwordTextField;
	@FXML
	TextField stateTextField;
	@FXML
	TextField streetTextField;
	@FXML
	Label userAlreadyExistsLabel;
	@FXML
	TextField usernameTextField;
	@FXML
	Label welcomeText;

	@FXML
	TextField zipcodeTextField;

	public void createNewAccount(final ActionEvent event) throws IOException, InterruptedException {

		this.userAlreadyExistsLabel.setVisible(false);
		this.invalidInput.setVisible(false);

		this.LOGGER.info("Selected new account button");

		final String username = this.usernameTextField.getText();
		final String password = this.passwordTextField.getText();
		final String firstName = this.firstNameTextField.getText();
		final String lastName = this.lastNameTextField.getText();
		final String state = this.stateTextField.getText();
		final String city = this.cityTextField.getText();
		final String zipcode = this.zipcodeTextField.getText();
		final String street = this.streetTextField.getText();
		final String houseNumber = this.houseNumberTextField.getText();
		final AccountType accountType = this.accountTypeChoiceBox.getValue();

		Account account;

		Stage stage;
		Scene scene;
		Parent root;

		if (!this.bank.doesUserExist(username)) {

			try {
				if (AccountType.CHECKING.equals(accountType)) {
					final Account thisAcc = this.bank.createNewCheckingAccount(username, password);
					account = thisAcc;
				} else if (AccountType.INVESTMENT.equals(accountType)) {
					final Account thisAcc = this.bank.createNewInvestmentAccount(username, password);
					account = thisAcc;
				} else {
					final Account thisAcc = this.bank.createNewSavingsAccount(username, password);
					account = thisAcc;
				}
				if (!username.isEmpty() && (username != null) && !password.isEmpty() && (password != null)) {
					if (!this.bank.login(account)) {
						this.LOGGER.success("Created new account " + account);
						this.bank.setUpAccount(account, firstName, lastName, state, city, zipcode, street, houseNumber);
						this.setAccount(account);
						final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AccountScene.fxml"));
						root = loader.load();
						stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					} else {
						this.LOGGER.severe("User exists or fatal bug");
					}
				} else {
					this.welcomeText.setVisible(false);
					this.invalidInput.setVisible(true);
					this.LOGGER.warn("Some field(s) are not filled out");
				}
			} catch (final LoadException e) {
				this.LOGGER.warn(e + " Exception");
				this.LOGGER.warn("Account username already exists or invalid input!");
				this.welcomeText.setVisible(false);
				this.invalidInput.setVisible(true);
			} catch (final NullPointerException e) {
				this.LOGGER.warn(e + " Exception");
				this.welcomeText.setVisible(false);
				this.invalidInput.setVisible(true);
			} catch (final java.lang.NumberFormatException e) {
				this.LOGGER.warn(e + " Exception, fields have not been filled out");
				this.welcomeText.setVisible(false);
				this.invalidInput.setVisible(true);
			} catch (final Exception e) {
				this.LOGGER.severe(e + " Unknown exception");
				this.welcomeText.setVisible(false);
				this.invalidInput.setVisible(true);
			}
		} else {
			this.LOGGER.warn("Account username already exists or invalid input!");
			this.welcomeText.setVisible(false);
			this.invalidInput.setVisible(true);
		}
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		this.invalidInput.setVisible(false);
		this.userAlreadyExistsLabel.setVisible(false);
		this.accountTypeChoiceBox.getItems().addAll(this.accountType);
	}
}
