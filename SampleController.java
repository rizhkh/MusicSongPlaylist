package application;

import java.awt.Button;
import java.awt.TextComponent;
import java.awt.*;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.event.ChangeListener;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class SampleController {
	
    @FXML private Text actiontarget;    
    //@FXML private  TextField p1;
    //@FXML TextField textField;
    @FXML TextField p2; // DONT REMOVE THIS FUCKER
    @FXML TextField p1;

	@FXML ListView<String> listView;
	@FXML ListView<String> detail;

	private ObservableList<String> obsList;
	private ObservableList<String> obsList2;

	//String[] g = {"hey","hey","hey","hey","hey","hey","hey","hey"};   
	ArrayList<String> songlist = new ArrayList<String>();

	//Add a datastructure that would reorganize the string array then show the array
	//next step is : whenever I click on any item in the list it shows
	//much more detail in the detail list box

	
	static int count = 0;
    @FXML
    private void addedname(ActionEvent event)
    {
    	String abc = p1.getText() ;
    	if(!abc.isEmpty())songlist.add(abc);	
	      if (!abc.isEmpty()) { 
	    	 // THIS PART UPDATES THE LIST WHEN NEW STUFF ADDED
	  		obsList = FXCollections.observableArrayList(songlist);
			listView.setItems(obsList); 
	    	  	    	   }     	
    }
    
    //WHEN YOU PRESS THE ADD BUTTON YOU GET AN ALERT
    // 
    // THIS METHOD WORKS WHEN YOU CLICK THE ADD BUTTON
    @FXML protected void adding(ActionEvent event) 
    {
    	p1.setVisible(true);
    	p2.setVisible(true);
    	addedname(event);
    }

    @FXML protected void deleting(ActionEvent event) 
    {
    	int del = listView.getSelectionModel().getSelectedIndex();
    	songlist.remove(del);
    	//THIS PART UPDATES THE LIST VIEW
  		obsList = FXCollections.observableArrayList(songlist);
		listView.setItems(obsList);     	
    }    
    
    
	public void start(Stage mainStage)
	{   p1.setVisible(false); 
		p2.setVisible(false); // So WHEN ADD IS CLICKED FORMS SHOW UP YEEHAA

		//Dont delete this part just yet
		//obsList = FXCollections.observableArrayList(songlist);
		//listView.setItems(obsList); 
		
		//THIS PART SHOWS WHATS IN THE DETAIL LIST
		obsList2 = FXCollections.observableArrayList(
				"Name of the song",
				"Name of the Artist",
				"Album name",
				"Release date"
				);
		detail.setItems(obsList2);
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
