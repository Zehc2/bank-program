package com.bank.engine.javafx;

import java.io.IOException;

import com.bank.engine.accounts.Account;
import com.bank.engine.utilitys.Logging;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BankFX extends Application {

	public static void main(final String[] args) {
		final Logging LOGGER = new Logging(Account.class.getName());
		LOGGER.info("Began program");
		Application.launch();
	}

	private final Logging LOGGER = new Logging(BankFX.class.getName());

	@Override
	public void start(final Stage primaryStage) {
		this.LOGGER.info("Loading start scene");
		try {
			final Parent root = FXMLLoader.load(this.getClass().getResource("StartScene.fxml"));
			final Scene scene = new Scene(root, Color.BEIGE);
			scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
			final Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
