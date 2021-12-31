package com.bank.engine.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.bank.engine.accounts.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepositSceneController extends Controller{
	
	Bank bank = new Bank();
	
	@FXML 
	TextField depositTextField;
	@FXML
	Label successDeposit;
	@FXML
	Label invalidAmount;
	
	public void deposit(ActionEvent e) throws IOException {
		successDeposit.setVisible(false);
		invalidAmount.setVisible(false);
	try {
		LOGGER.info("Selected deposit button");
		successDeposit.setVisible(false);
		bank.cashDeposit(Integer.valueOf(depositTextField.getText()), null, super.getAccount());
		successDeposit.setText("Successfully deposited $" + depositTextField.getText());
		successDeposit.setVisible(true);
		depositTextField.clear();
		} catch(NumberFormatException error) {
			invalidAmount.setVisible(true);
			LOGGER.warn(error);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		successDeposit.setVisible(false);
		invalidAmount.setVisible(false);
	}	
}
