package com.bank.engine.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BalanceSceneController extends Controller {

	@FXML
	Label balanceLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.updateBalance();
	}

	public void updateBalance() {
		this.balanceLabel.setText("Balance: " + "$" + bank.getBalance(getAccount()));
	}
}
