package com.bank.engine.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountInfoSceneController extends Controller {
	
	@FXML
	Label usernameLabel;
	@FXML
	Label fullnameLabel;
	@FXML
	Label bankidLabel;
	@FXML
	Label stateLabel;
	@FXML
	Label zipcodeLabel;
	@FXML 
	Label cityLabel;
	@FXML
	Label streetLabel;
	@FXML 
	Label housenumberLabel;
	
	@Override
	public void startScene() {
		setLabels();
	}
	
	private void setLabels() {
		usernameLabel.setText(usernameLabel.getText() + getAccount().getUsername());
		fullnameLabel.setText(fullnameLabel.getText() + getAccount().getFirstName() + " " + getAccount().getLastName());
		bankidLabel.setText(bankidLabel.getText() + getAccount().getBankID());
		stateLabel.setText(stateLabel.getText() + getAccount().getState());
		zipcodeLabel.setText(zipcodeLabel.getText() + getAccount().getZip());
		cityLabel.setText(cityLabel.getText() + getAccount().getCity());
		streetLabel.setText(streetLabel.getText() + getAccount().getStreet());
		housenumberLabel.setText(housenumberLabel.getText() + getAccount().getHouseNumber());
	}
}
