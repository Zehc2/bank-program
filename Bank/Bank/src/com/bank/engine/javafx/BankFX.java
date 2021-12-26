package com.bank.engine.javafx;
	
import java.io.IOException;

import com.bank.engine.accounts.Account;
import com.bank.engine.utilitys.Logging;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.fxml.*;

public class BankFX extends Application {
	
	public static void main(String[] args) {
	    Logging LOGGER = new Logging(Account.class.getName());
		LOGGER.info("Began program");
		launch();
	}
	
	private Logging LOGGER = new Logging(BankFX.class.getName());
	
	@Override
	public void start(Stage primaryStage) {	
		LOGGER.info("Loading start scene");
		try {
		Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
		Scene scene = new Scene(root, Color.BEIGE);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
