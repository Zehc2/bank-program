module JavaFXGUI {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.base;
	requires java.logging;
	
	opens com.bank.engine.javafx to javafx.graphics, javafx.fxml;
}
