package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.TextField;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.*;

public class SampleController {
	
	static int check=0;
	
    @FXML private Text actiontarget;    
    @FXML TextField p2; // DONT REMOVE THIS FUCKER
    @FXML TextField p1;
    @FXML TextField p3;
    @FXML TextField p4;    

	@FXML ListView<String> listView;
	@FXML ListView<String> detail;

	static File file = new File("stuff2.txt");	
	static 	FileWriter fileWriter ;  
	static   BufferedWriter bufferedWriter;
	
	private ObservableList<String> obsList;
	private ObservableList<String> obsList2;
	int[][] code = new int[50][4];
	
	//String[] g = {"hey","hey","hey","hey","hey","hey","hey","hey"};   
	ArrayList<String> songlist = new ArrayList<String>();
	//HashMap<Integer, String> songdetail = new HashMap<>(); // Integer is the index of the name
	String[][] songdetail = new String [100][5];
	
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
				
    		try {
					makeaplaylist(bufferedWriter,abc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		
    		detailadd(abc) ; 
    	}
	      if (!abc.isEmpty()) 
	      { 
	    	  
	    	 // THIS PART UPDATES THE LIST WHEN NEW STUFF ADDED
	  		obsList = FXCollections.observableArrayList(songlist);
			listView.setItems(obsList); 
			
			//THIS PART IS TAKEN OUT TEMPORARILY AS IT WAS CAUSING PROBLEM DISPLAYING DETAIL LIST
			//MAKE THIS TO THE NEWEST ADDED SONG WHEN ALPHABETICALLY SELECTED
			//listView.getSelectionModel().select(count);
			
			detailadd(abc) ; 
			
			//THIS SELECTS THE NEWEST ADDED SONG AND UPDATES THE COUNTER
			count++;
	      }     	
    }
    
  
	public void detailadd(String abc)
	{
	
		//ADD A CONDITION THAT MAKES SURE P1 OR P2 ARE NOT EMPTY
		int id = songlist.indexOf(abc);
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
    	System.out.println(count + " " + songlist);
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
    
    public void makeaplaylist(BufferedWriter bufferedWriter,String abc) throws IOException
    {
    	
    	//fileWriter = new FileWriter("stuff2.txt", true );  
        //bufferedWriter = new BufferedWriter(fileWriter);
    	
    	System.out.println("INSIDE makeaplaylist()");
    	try {
        	bufferedWriter.write(abc);
			bufferedWriter.newLine();
			System.out.println("should be there" + abc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	//bufferedWriter.close();
    	//fileWriter.close();
    }
   
    public void readPlaylist() throws FileNotFoundException
    {
    	System.out.println( " # Inside readPlaylist() " );
        file = new File("stuff2.txt"); 
        Scanner readerPlaylist = new Scanner(file);       	
    	
        while( readerPlaylist.hasNextLine() )
        {
        	System.out.println( "$ " + readerPlaylist.nextLine() );
        }
    	
		//songlist.add(abc);	
  		//obsList = FXCollections.observableArrayList(songlist);
		//listView.setItems(obsList); 
    }
    
	public void start(Stage mainStage) throws IOException
	{   p1.setVisible(false); 
		p2.setVisible(false); // So WHEN ADD IS CLICKED FORMS SHOW UP YEEHAA
    	p3.setVisible(false);
    	p4.setVisible(false);
    	
    	fileWriter = new FileWriter(file, true );  
        bufferedWriter = new BufferedWriter(fileWriter);
  
      
        if(file.isFile())
        {
        	if(file.canRead())
        	{
        		readPlaylist();
        	}
        }
        
    	
		obsList2 = FXCollections.observableArrayList(
				"Name of the song",
				"Name of the Artist",
				"Album name",
				"Release date"
				);
		detail.setItems(obsList);
		detail.setItems(obsList2);
		

		//if(count>0) //items in the arraylist that will be shown in the listview
		//{
	    listView.getSelectionModel().select(0); 
	    
	    updater(mainStage);
	    
    	//bufferedWriter.close();
    	//fileWriter.close();
		//}
	    
	    //To terminate the application correctly
	    mainStage.setOnCloseRequest(event -> pgmclosed());
	    //mainStage.setOnCloseRequest();
	    //ActionEvent event = null ;
	    //mainStage.setOnCloseRequest((EventHandler<WindowEvent>) event -> pgmclosed());
	}

	//*********************************8
	// Add JOptionPane pop dialog box here that confirms if you want to exit
	//
	public void pgmclosed()
	{
		try {
			bufferedWriter.close();
	    	fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.exit(0);
	}
	
	private void updater(Stage mainStage) {  
	      listView.getSelectionModel().selectedIndexProperty().addListener(
		           (obs, oldVal, newVal) -> selected(mainStage));	    
	}
	
	
	//THIS METHOD DISPLAYS DETAILS OF SELECTED SONG
	private void selected(Stage mainStage) {  

	      //String item = listView.getSelectionModel().getSelectedItem();
		int id=  listView.getSelectionModel().getSelectedIndex() ;
		System.out.println("$ : " + id);

		System.out.println(songdetail[id][0]);
		System.out.println(songdetail[id][1]);
		System.out.println(songdetail[id][2]);
		System.out.println(songdetail[id][3]);


}
