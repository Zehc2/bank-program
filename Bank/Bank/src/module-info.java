module JavaFXGUI {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.base;
	requires java.logging;
	requires java.sql;
	
	opens com.bank.engine.javafx to javafx.graphics, javafx.fxml;
}
