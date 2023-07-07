package gui;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Screen.fxml"));
		Scene scene = new Scene(root);

		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		
		stage.setScene(scene);
		stage.setTitle("SNMP Manager");
		stage.setResizable(true);
		stage.setMinHeight(610);
		stage.setMinWidth(620);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
