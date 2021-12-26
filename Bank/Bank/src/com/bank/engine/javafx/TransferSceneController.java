package com.bank.engine.javafx;

import java.io.IOException;

import com.bank.engine.accounts.Account;
import com.bank.engine.banking.Bank;
import com.bank.engine.utilitys.AccountFileUtilitys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class TransferSceneController extends Controller {
	
	Bank bank = new Bank();

	private AccountFileUtilitys fileUtils = new AccountFileUtilitys();
	
	
	@FXML
	Label failedTransfer;
	@FXML 
	TextField transferTextField;
	@FXML
	TextField transfereeAccountTextField;
	@FXML
	Label successTransfer;
	
	@Override
	protected void startScene() {
		successTransfer.setVisible(false);
		failedTransfer.setVisible(false);	
	}	

	public void transfer(ActionEvent e) throws IOException {
		
		successTransfer.setVisible(false);
		failedTransfer.setVisible(false);
		
	try {
			
		String accountName = transfereeAccountTextField.getText();
		Account transfereeAccount = fileUtils.getUserWithUsername(accountName);	
		
	try {
		
		LOGGER.info("Selected transfer button");
		successTransfer.setVisible(false);
		failedTransfer.setVisible(false);
		
		if(bank.transfer(Integer.valueOf(transferTextField.getText()), getAccount(), transfereeAccount)) {
			successTransfer.setText("Successfully transfered $" + transferTextField.getText() + " to " + transfereeAccount);
			successTransfer.setVisible(true);
			transferTextField.clear();
			transfereeAccountTextField.clear();
		} else {
			failedTransfer.setVisible(true);
		}
		
		} catch(NumberFormatException error) {
			failedTransfer.setVisible(true);
			LOGGER.warn(error);
		}
	} catch(NullPointerException error) {
		failedTransfer.setVisible(true);
		LOGGER.warn(error + " User does not exist");
		}
	}
}
