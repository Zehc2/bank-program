package com.bank.engine.javafx;

import java.io.IOException;

import com.bank.engine.accounts.Account;
import com.bank.engine.accounts.CheckingAccount;
import com.bank.engine.banking.Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAccountSceneController extends Controller {
	
	Bank bank = new Bank();
	
	@FXML
	TextField usernameTextField;
	@FXML
	TextField passwordTextField;
	@FXML
	TextField firstNameTextField;
	@FXML
	TextField lastNameTextField;
	@FXML
	TextField cityTextField;
	@FXML
	TextField zipcodeTextField;
	@FXML
	TextField streetTextField;
	@FXML
	TextField houseNumberTextField;
	@FXML
	TextField stateTextField;
	@FXML
	Label invalidInput;
	@FXML
	Label welcomeText;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private boolean isInteger(String num) {
		try {
			 @SuppressWarnings("unused")
			int myNum = Integer.parseInt(num);
			  return true;
			} catch (NumberFormatException e) {
				return false;
			}
	}
	
	public void setErrorMessageFalse() {
		invalidInput.setVisible(false);
	}
	
	public void createNewAccount(ActionEvent event) throws IOException, InterruptedException {	
		
		LOGGER.info("Selected new account button");
		
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String state = stateTextField.getText();
		String city = cityTextField.getText();
		String zipcode = zipcodeTextField.getText();
		String street = streetTextField.getText();
		String houseNumber = houseNumberTextField.getText();
		
		try {					
		if(isInteger(zipcode) && isInteger(houseNumber)) {
			Account account = new CheckingAccount(username, password);			
			if(account.login() == false) {	
				
				LOGGER.success("Created new account " + account);
				account.setName(firstName, lastName);
				account.setAddress(state, city, Integer.valueOf(zipcode), street, Integer.valueOf(houseNumber));
				account.openAccount();
				account.login();
				
				setAccount(account);	
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountScene.fxml"));
				LOGGER.info("Switched to account scene");				
				root = loader.load();		
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();	
				AccountSceneController accountScene = loader.getController();
				accountScene.startScene();
				}	
			} 	else {
				LOGGER.warn("Invalid input");
				welcomeText.setVisible(false);
				invalidInput.setVisible(true);						
			}
		} catch (Exception e) {
			LOGGER.warn(e + "Invalid input");
			welcomeText.setVisible(false);
			invalidInput.setVisible(true);			
			}		
		}

	@Override
	public void startScene() {	
	}
}

