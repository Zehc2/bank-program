package com.bank.engine.javafx;


import java.net.URL;
import java.util.ResourceBundle;

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
	@FXML
	Label accountTypeLabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setLabels();
	}
	
	private void setLabels() {
		usernameLabel.setText(usernameLabel.getText() + bank.getUsername(getAccount()));
		fullnameLabel.setText(fullnameLabel.getText() + bank.getFirstName(getAccount()) + " " + bank.getLastName(getAccount()));
		bankidLabel.setText(bankidLabel.getText() + bank.getID(getAccount()));
		stateLabel.setText(stateLabel.getText() + bank.getState(getAccount()));
		zipcodeLabel.setText(zipcodeLabel.getText() + bank.getZIP(getAccount()));
		cityLabel.setText(cityLabel.getText() + bank.getCity(getAccount()));
		streetLabel.setText(streetLabel.getText() + bank.getStreet(getAccount()));
		housenumberLabel.setText(housenumberLabel.getText() + bank.getHouseNumber(getAccount()));
		accountTypeLabel.setText(accountTypeLabel.getText() + bank.getAccountType(getAccount()));
	}
}
