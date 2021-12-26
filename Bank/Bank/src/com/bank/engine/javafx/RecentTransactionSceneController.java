package com.bank.engine.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class RecentTransactionSceneController extends Controller  {
	
	@FXML
	Label pastTransactions;

	private void setTransactions() {
		pastTransactions.setText("This will work when database set up!");
	}

	@Override
	protected void startScene() {
		setTransactions();
	}	
}
