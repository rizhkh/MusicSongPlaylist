package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));		
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(getClass().getResource("Sample.fxml"));
			BorderPane root = (BorderPane)loader.load() ; 
			//AnchorPane root = (AnchorPane)loader.load();
			//SampleController sc = 

			SampleController listController = loader.getController();       
			listController.start(primaryStage); 					
					
					
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
