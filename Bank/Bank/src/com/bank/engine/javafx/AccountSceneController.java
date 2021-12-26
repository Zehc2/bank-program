package com.bank.engine.javafx;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.bank.engine.banking.Bank;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountSceneController extends Controller {
	
	Bank bank = new Bank();
	
	@FXML
	Label welcomeMessage;
	@FXML 
	Label DateTime;

	@Override
	protected void startScene() {
		welcomeMessage.setText("Welcome " + getAccount().getFirstName() + "!");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		DateTime.setText(dtf.format(now));
	}
}

