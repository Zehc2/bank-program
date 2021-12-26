package com.bank.engine.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BalanceSceneController extends Controller {

	@FXML
	Label balanceLabel;

	@Override
	public void startScene() {
		this.updateBalance();
	}

	public void updateBalance() {
		this.balanceLabel.setText("Balance: " + "$" + super.getAccount().getBalance());
	}
}
