package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import songlibview.SampleController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import stwolib.songlibstuff;

public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("songlibview.Sample.fxml"));		
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(getClass().getResource("/songlibview/Sample.fxml"));
			BorderPane root = (BorderPane)loader.load() ; 
			//AnchorPane root = (AnchorPane)loader.load();
			//SampleController sc = 

			SampleController listController = loader.getController();       
			listController.start(primaryStage); 					
					
					
			Scene scene = new Scene(root,660,500);
			scene.getStylesheets().add(getClass().getResource("/songlibview/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
