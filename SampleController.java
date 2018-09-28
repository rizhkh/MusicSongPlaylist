package application;

import javafx.event.ActionEvent;

import java.util.ArrayList;



import javafx.scene.control.TextField;

import com.sun.glass.events.MouseEvent;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class SampleController {
	
	static int check=0;
	
    @FXML private Text actiontarget;    
    //@FXML private  TextField p1;
    //@FXML TextField textField;
    @FXML TextField p2; // DONT REMOVE THIS FUCKER
    @FXML TextField p1;
    @FXML TextField p3;
    @FXML TextField p4;    

	@FXML ListView<String> listView;
	@FXML ListView<String> detail;

	private ObservableList<String> obsList;
	private ObservableList<String> obsList2;
	int[][] code = new int[50][4];
	
	//String[] g = {"hey","hey","hey","hey","hey","hey","hey","hey"};   
	ArrayList<String> songlist = new ArrayList<String>();
	//HashMap<Integer, String> songdetail = new HashMap<>(); // Integer is the index of the name
	String[][] songdetail = new String [100][4];
	
	//Add a datastructure that would reorganize the string array then show the array
	//next step is : whenever I click on any item in the list it shows
	//much more detail in the detail list box

	
		
	static int count = 0;
    @FXML
    private void addedname(ActionEvent event)
    {
    	String abc =  p1.getText();
    	String artist = p2.getText();
    	
    	if( !abc.isEmpty() )
    	{
    	abc = p1.getText() + "-" + p2.getText();
    	}
    	
    	if( !abc.isEmpty() && artist.isEmpty() )
    	{
    	abc = p1.getText();
    	}    	
    	
    	if(!abc.isEmpty())
    	{
    		songlist.add(abc);	
    		/*
    		int id = songlist.indexOf(abc);
    		String z = new Integer(id).toString();
    		songdetail[id][0]=abc;
    		songdetail[id][1]=p2.getText();
    		songdetail[id][2]=p3.getText();
    		songdetail[id][3]=p4.getText();
   
    		//make this a separate method
    		//This part basically updates the detail list window with info of details added
    		obsList2 = FXCollections.observableArrayList(
    				abc,
    				songdetail[id][1],
    				songdetail[id][2],
    				songdetail[id][3]
    				);
    		detail.setItems(obsList2);   
    		*/
    		detailadd(abc) ; 
    	}
	      if (!abc.isEmpty()) 
	      { 
	    	  
	    	 // THIS PART UPDATES THE LIST WHEN NEW STUFF ADDED
	  		obsList = FXCollections.observableArrayList(songlist);
			listView.setItems(obsList); 
			listView.getSelectionModel().select(count);
			
			detailadd(abc) ; 
			
			//THIS SELECTS THE NEWEST ADDED SONG AND UPDATES THE COUNTER
			count++;
	      }     	
    }
  
	public void detailadd(String abc)
	{
	
		int id = songlist.indexOf(abc);
		String z = new Integer(id).toString();
		songdetail[id][0]=abc;
		songdetail[id][1]=p2.getText();
		songdetail[id][2]=p3.getText();
		songdetail[id][3]=p4.getText();
	
		//make this a separate method
		//This part basically updates the detail list window with info of details added
		obsList2 = FXCollections.observableArrayList(
				abc,
				songdetail[id][1],
				songdetail[id][2],
				songdetail[id][3]
				);
		detail.setItems(obsList2);    	
	}
    
    public void iclicker(Stage mainStage)
    {
    	System.out.println("IN THE ICLICKER METHOD");
    	String selectedsong = listView.getSelectionModel().getSelectedItem(); 
		int id = songlist.indexOf(selectedsong);
		//make this a separate method
		obsList2 = FXCollections.observableArrayList(
				songdetail[id][0],
				songdetail[id][1],
				songdetail[id][2],
				songdetail[id][3]
				);    	
		detail.setItems(obsList2); 		
    }

    
    //WHEN YOU PRESS THE ADD BUTTON YOU GET AN ALERT
    // 
    // THIS METHOD WORKS WHEN YOU CLICK THE ADD BUTTON
    @FXML protected void adding(ActionEvent event) 
    {
    	check++;
    	
    	p1.setVisible(true);
    	p2.setVisible(true);
    	p3.setVisible(true);
    	p4.setVisible(true);
    	addedname(event);
    }

    
    @FXML protected void deleting(ActionEvent event) 
    {
    	int del = listView.getSelectionModel().getSelectedIndex();
    	
    	if(del>=0)
    	{
    	songlist.remove(del);
    	//THIS PART UPDATES THE LIST VIEW
  		obsList = FXCollections.observableArrayList(songlist);
		listView.setItems(obsList);    
		count--;
		//listView.getSelectionModel().select(count);
		//WHEN SONG IS DELETED SELECTED CURSOR MOVES UP
    	}
    }    
    
   
	public void start(Stage mainStage)
	{   p1.setVisible(false); 
		p2.setVisible(false); // So WHEN ADD IS CLICKED FORMS SHOW UP YEEHAA
    	p3.setVisible(false);
    	p4.setVisible(false);
    	
		obsList2 = FXCollections.observableArrayList(
				"Name of the song",
				"Name of the Artist",
				"Album name",
				"Release date"
				);
		detail.setItems(obsList);
		detail.setItems(obsList2);
	    listView.getSelectionModel().select(0); 
	    System.out.println(":)");
	}

	public void selected(Stage mainStage) {                
	      //String item = listView.getSelectionModel().getSelectedItem();
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
		          //System.out.println("not blocking");
	   }
}
