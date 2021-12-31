package com.bank.engine.javafx;


import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountSceneController extends Controller {

	@FXML
	Label welcomeMessage;
	@FXML 
	Label DateTime;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		welcomeMessage.setText("Welcome " + bank.getFirstName(getAccount()) + "!");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		DateTime.setText(dtf.format(now));
	}
}

