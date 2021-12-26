package com.bank.engine.javafx;

import java.io.IOException;
import com.bank.engine.banking.Bank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WithdrawalSceneController extends Controller {
	
	Bank bank = new Bank();
	
	@FXML 
	TextField withdrawalTextField;
	@FXML
	Label successfullWithdrawal;
	@FXML 
	Label errorMessage;
	@FXML
	Label invalidAmount;
	
	@Override
	protected void startScene() {
		successfullWithdrawal.setVisible(false);
		errorMessage.setVisible(false);
		invalidAmount.setVisible(false);
	}
	
	public void withdrawal(ActionEvent e) throws IOException {	
		LOGGER.info("Selected withdrawal button");	
	try {
		successfullWithdrawal.setVisible(false);
		errorMessage.setVisible(false);
		invalidAmount.setVisible(false);
		
		if(bank.cashWithdrawal(Integer.valueOf(withdrawalTextField.getText()), null, getAccount())) {
			successfullWithdrawal.setText("Successfully withdrew $" + withdrawalTextField.getText());		
			successfullWithdrawal.setVisible(true);
		} else {
			LOGGER.warn("Account does not have sufficcient funds");
			errorMessage.setVisible(true);
		}
		withdrawalTextField.clear();
		} catch(NumberFormatException error) {
			invalidAmount.setVisible(true);
			LOGGER.warn(error);
		}
	}
}
