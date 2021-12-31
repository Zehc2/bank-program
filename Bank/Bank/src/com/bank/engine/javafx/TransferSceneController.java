package com.bank.engine.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.bank.engine.accounts.Account;
import com.bank.engine.accounts.Bank;
import com.bank.engine.utilitys.DataBaseUtilitys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransferSceneController extends Controller {

	Bank bank = new Bank();

	DataBaseUtilitys dataBase = new DataBaseUtilitys();

	@FXML
	Label failedTransfer;
	@FXML
	Label successTransfer;
	@FXML
	TextField transfereeAccountTextField;
	@FXML
	TextField transferTextField;

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		this.successTransfer.setVisible(false);
		this.failedTransfer.setVisible(false);
	}

	public void transfer(final ActionEvent e) throws IOException {

		this.successTransfer.setVisible(false);
		this.failedTransfer.setVisible(false);

		try {

			final String accountName = this.transfereeAccountTextField.getText();
			final Account transfereeAccount = this.dataBase.getUserWithUsername(accountName);

			try {

				this.LOGGER.info("Selected transfer button");
				this.successTransfer.setVisible(false);
				this.failedTransfer.setVisible(false);
				if (this.bank.doesUserExist(accountName)) {
					if (this.bank.transfer(Integer.parseInt(this.transferTextField.getText()), Controller.getAccount(),
							transfereeAccount)) {
						this.successTransfer.setText("Successfully transfered $" + this.transferTextField.getText()
								+ " to " + transfereeAccount);
						this.successTransfer.setVisible(true);
						this.transferTextField.clear();
						this.transfereeAccountTextField.clear();
					} else {
						this.failedTransfer.setVisible(true);
					}
				} else {

				}
			} catch (final NumberFormatException error) {
				this.failedTransfer.setVisible(true);
				this.LOGGER.warn(error);
			}
		} catch (final NullPointerException error) {
			this.failedTransfer.setVisible(true);
			this.LOGGER.warn(error + " User does not exist");
		}

	}
}
