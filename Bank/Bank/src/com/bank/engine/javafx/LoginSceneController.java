package com.bank.engine.javafx;

import java.io.IOException;

import com.bank.engine.accounts.Account;
import com.bank.engine.accounts.CheckingAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneController extends Controller {
	
	@FXML
	TextField usernameTextField;
	@FXML
	TextField passwordTextField;
	@FXML 
	Label errorMessage;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@Override
	public void startScene() {
		errorMessage.setVisible(false);
	}
	
	public void login(ActionEvent event) throws IOException {	
		
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();	
		
		Account account = new CheckingAccount(username, password);	
		
			if(account.login() && !username.isEmpty() && !password.isEmpty()) {
				LOGGER.info("Switched to account scene");		
				setAccount(account);
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountScene.fxml"));						
		    	root = loader.load();
		    	AccountSceneController accountScene = loader.getController();	
		    	accountScene.startScene();
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);				
				stage.show();
				
		} else {
			LOGGER.warn("Invalid username or password");
			errorMessage.setVisible(true);		
		}
	}
}
