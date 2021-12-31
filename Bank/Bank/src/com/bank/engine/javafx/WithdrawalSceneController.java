package com.bank.engine.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.bank.engine.accounts.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WithdrawalSceneController extends Controller {

	Bank bank = new Bank();

	@FXML
	Label errorMessage;
	@FXML
	Label invalidAmount;
	@FXML
	Label successfullWithdrawal;
	@FXML
	TextField withdrawalTextField;

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		this.successfullWithdrawal.setVisible(false);
		this.errorMessage.setVisible(false);
		this.invalidAmount.setVisible(false);
	}

	public void withdrawal(final ActionEvent e) throws IOException {
		this.LOGGER.info("Selected withdrawal button");
		try {
			this.successfullWithdrawal.setVisible(false);
			this.errorMessage.setVisible(false);
			this.invalidAmount.setVisible(false);

			if (this.bank.cashWithdrawal(Integer.parseInt(this.withdrawalTextField.getText()), null,
					Controller.getAccount())) {
				this.successfullWithdrawal.setText("Successfully withdrew $" + this.withdrawalTextField.getText());
				this.successfullWithdrawal.setVisible(true);
			} else {
				this.LOGGER.warn("Account does not have sufficcient funds");
				this.errorMessage.setVisible(true);
			}
			this.withdrawalTextField.clear();
		} catch (final NumberFormatException error) {
			this.invalidAmount.setVisible(true);
			this.LOGGER.warn(error);
		}
	}
}
