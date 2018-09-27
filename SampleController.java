package application;

import java.awt.Button;
import java.awt.TextComponent;
import java.awt.TextField;
import javafx.event.ActionEvent;
import java.util.Optional;
import javafx.scene.control.Label;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class SampleController {
	
    @FXML private Text actiontarget;    
    
    //WHEN YOU PRESS THE ADD BUTTON YOU GET AN ALERT
    @FXML protected void sayHelloWorld(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText("Look, an Information Dialog");
    	alert.setContentText("I have a great message for you!");
    	alert.showAndWait();
    	}

	@FXML ListView<String> listView;
	@FXML ListView<String> detail;


	private ObservableList<String> obsList;

	
	public void start(Stage mainStage)
	{
		obsList = FXCollections.observableArrayList("Africa by Toto",
				"Splunk by R",
				"Yoyo by Ma"
				);
		listView.setItems(obsList); 
		
		// select the first item
	      listView.getSelectionModel().select(0);

	      // set listener for the items
	      //listView.getSelectionModel().selectedIndexProperty().addListener(
	      //(obs, oldVal, newVal)->showItemInputDialog(mainStage));

	
	}
/*	
	private void showItem(Stage mainStage) {                
	      Alert alert = 
	         new Alert(AlertType.INFORMATION);
	      //alert.initModality(Modality.NONE);
	      alert.initOwner(mainStage);
	      alert.setTitle("List Item");
	      alert.setHeaderText(
	           "Selected list item properties");

	      String content = "Index: " + 
	          listView.getSelectionModel()
	                   .getSelectedIndex() + 
	          "\nValue: " + 
	          listView.getSelectionModel()
	                   .getSelectedItem();

	          alert.setContentText(content);
	          alert.showAndWait();
	          //System.out.println("not blocking");
	   }
	
	private void showItemInputDialog(Stage mainStage) {                
	      String item = listView.getSelectionModel().getSelectedItem();
	      int index = listView.getSelectionModel().getSelectedIndex();

	      TextInputDialog dialog = new TextInputDialog(item);
	      dialog.initOwner(mainStage); dialog.setTitle("List Item");
	      dialog.setHeaderText("Selected Item (Index: " + index + ")");
	      dialog.setContentText("Enter name: ");

	      Optional<String> result = dialog.showAndWait();
	      if (result.isPresent()) { obsList.set(index, result.get()); }
	   }
*/
	/*
	public void add(ActionEvent e)
	{
		//Button a = (Button)a.getSource();
		if(Button==ad1)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("z");
			alert.setHeaderText("Information Alert");
			String s ="This is an example of JavaFX 8 Dialogs... ";
			alert.setContentText(s);
			alert.show();	
		}
	}
	*/
}
