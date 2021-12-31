package com.bank.engine.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class RecentTransactionSceneController extends Controller  {
	
	@FXML
	Label pastTransactions;

	private void setTransactions() {
		pastTransactions.setText("This will work when database set up!");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		setTransactions();
	}	
}
