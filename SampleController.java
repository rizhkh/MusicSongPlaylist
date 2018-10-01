package application;

import javafx.event.ActionEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class SampleController {
	
	// NOTE: PROBLEM - WHEN YOU ADD AB-AB AND AB-AB AGAIN IT ADDS IT SHOULDNT
	
	//static int check=0;
	
    @FXML private Text actiontarget;    
    @FXML TextField p2; 
    @FXML TextField p1;
    @FXML TextField p3;
    @FXML TextField p4;
    @FXML Label l1;        
	@FXML ListView<String> listView;
	@FXML ListView<String> detail;

	static File file = new File("stuff2.txt");	
	static 	FileWriter fileWriter ;  
	static   BufferedWriter bufferedWriter;
	
	private ObservableList<String> obsList;
	private ObservableList<String> obsList2;
	int[][] code = new int[50][4];

	ArrayList<String> songlist = new ArrayList<String>();
	//HashMap<Integer, String> songdetail = new HashMap<>(); // Integer is the index of the name
	String[][] songdetail = new String [100][5];

	public Stage secondaryStage;
	
		
	static int count = 0;
	
	
///////##########################################3
/////
	
	//This method moves the array indexes to the right spots 
	
    public void addingPositionchange(int id)
    {

    	for(int i=count; i>=id; i--)
    	{
    		songdetail[i+1][0] = songdetail[i][0];
        	songdetail[i+1][1] = songdetail[i][1];    	
        	songdetail[i+1][2] = songdetail[i][2];      	
        	songdetail[i+1][3] = songdetail[i][3];
    	} 
    	
  		obsList = FXCollections.observableArrayList(songlist);
		listView.setItems(obsList);  	 	
    }
	
	
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

    		int idforcheck1 = songlist.indexOf(p1.getText());
    		//System.out.println("P1 " + p1.getText());
    		//System.out.println("P2 " + p2.getText());
    		//System.out.println("Passed string " + abc);
     		//System.out.println("String in arraylist " + songlist.get(idforcheck1));
	    		if( songlist.contains(abc) )
	    		{
	    			System.out.println("yes");
	    			/*
	    			int idforcheck = songlist.indexOf(p1.getText());
	    			String artistname = songlist.get(idforcheck);
	    			
	    			boolean check=false;
	    			
	    			String a = songdetail[idforcheck][1];
	    			System.out.println(a);
	    			String b = p2.getText();
	    			
	    			if(p2.getText()==null & abc.equals(artistname))
	    			{
	    				System.out.println("\n\n\n %%%%%%%%%%%% \n\n\n");
	    			}
	    			
	    			if( a.equals(b) )//a==b )
	    			{
	    				System.out.println("yesyes");
	    				check=true;
	    			}
	
	    			if(check==true)
	    			
	    			{
	    			*/
	    				System.out.println("true2");
	    			      Alert alert = new Alert(AlertType.INFORMATION);
	    			 	      alert.setTitle("Already exist");
	    			 	      alert.setHeaderText(
	    			 	           "Duplicate found.Can't be added");
	    			          Optional<ButtonType> option = alert.showAndWait();
	    			          
	    			          if (option.get() == ButtonType.OK) 
	    			          {   }  			 	      
	    		/*	}
	    			
	    			else
	    			{
	    	    		songlist.add(abc);
	    	    		Collections.sort(songlist);
	    	    		int id2 = songlist.indexOf(abc);
	    	    		addingPositionchange(id2);
	    	    		detailadd(abc) ; 	
	    			}*/
	    		}
    		
	    		else
	    		{
		    		System.out.println("no");
		    		System.out.println( p1.getText() );
		    		songlist.add(abc);
		    		Collections.sort(songlist,String.CASE_INSENSITIVE_ORDER);
		    		int id2 = songlist.indexOf(abc);
		    		addingPositionchange(id2);
		    		detailadd(abc) ; 
	    		}
    		
    		/*
       		songlist.add(abc);
    		Collections.sort(songlist);
    		int id2 = songlist.indexOf(abc);
    		addingPositionchange(id2);
    		detailadd(abc) ; 
    		 */
    	}
	      if (!abc.isEmpty()) 
	      { 
	    	  
	    	 // THIS PART UPDATES THE LIST WHEN NEW STUFF ADDED
	  		obsList = FXCollections.observableArrayList(songlist);
			listView.setItems(obsList); 

			listView.getSelectionModel().select(count);
			
			//detailadd(abc) ; 
			
			//THIS SELECTS THE NEWEST ADDED SONG AND UPDATES THE COUNTER
			count++;
	      }     	
	      p1.clear();
	      p2.clear();
	      p3.clear();
	      p4.clear();
    }
    
  
//////// #############################################################
////////
    
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
				songdetail[id][3]);
		detail.setItems(obsList2);    	
	}

	
////////#############################################################
////////
    //WHEN YOU PRESS THE ADD BUTTON YOU GET AN ALERT
    // 
    // THIS METHOD WORKS WHEN YOU CLICK THE ADD BUTTON
 	@FXML protected void adding(ActionEvent event) 
    {
    	//check++;
    	p1.setVisible(true);
    	p2.setVisible(true);
    	p3.setVisible(true);
    	p4.setVisible(true);
    	addedname(event);
    	System.out.println(count + " " + songlist); 
    }

//static int editCheck=0;	


////////#############################################################
////////
    @FXML protected void editing(ActionEvent event) 
    {
      	l1.setVisible(true);
    	p1.setVisible(true);
    	p2.setVisible(true);
    	p3.setVisible(true);
    	p4.setVisible(true);
    	//System.out.println(listView.getSelectionModel().getSelectedIndex());

    	int id = listView.getSelectionModel().getSelectedIndex();
    	
    	if(!p1.getText().isEmpty() || !p2.getText().isEmpty())
    	{
      	  Alert alert = new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Remove");
          alert.setHeaderText("Are you sure want to edit this song?");

          Optional<ButtonType> option = alert.showAndWait();
          String abc = "";
          if (option.get() == ButtonType.OK) 
          {     	
	    	abc = p1.getText();
	    	System.out.println(abc);
	    	songlist.set(id, abc);
	    	songdetail[id][0]=abc;
	    	System.out.println(songlist);
	  		obsList = FXCollections.observableArrayList(songlist);
			listView.setItems(obsList); 
			
			if(!p2.getText().isEmpty())
			{
				abc = p2.getText();
				songdetail[id][1]=abc;
			}
			
			if(!p3.getText().isEmpty())
			{
				abc = p3.getText();
				songdetail[id][2]=abc;
			}			

			if(!p4.getText().isEmpty())
			{
				abc = p4.getText();
				songdetail[id][3]=abc;
			}					
          }
    	}
    }
	
	
////////#############################################################
////////  
    
    public void datachangeAfterDelete(int id)
    {
    	for(int i=id; i<songlist.size(); i++)
    	{
        	songdetail[i][0] = songdetail[i+1][0];
        	songdetail[i][1] = songdetail[i+1][1];    	
        	songdetail[i][2] = songdetail[i+1][2];      	
        	songdetail[i][2] = songdetail[i+1][2];     		
    	}

    	//System.out.println("datachangeAfterDelet : " +  songdetail[id][0]);    	
    }
    
    
    @FXML protected void deleting(ActionEvent event) 
    {
    	  Alert alert = new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Remove");
          alert.setHeaderText("Are you sure want to delete this song?");

          Optional<ButtonType> option = alert.showAndWait();
          
          if (option.get() == ButtonType.OK) 
          { 	
		    	int del = listView.getSelectionModel().getSelectedIndex();
		    	
		    	if(del>=0)
		    	{
		    	songlist.remove(del);
		    	datachangeAfterDelete(del);
		    	System.out.println(songlist);
		    	//THIS PART UPDATES THE LIST VIEW
		  		obsList = FXCollections.observableArrayList(songlist);
				listView.setItems(obsList);    
				count--;
				listView.getSelectionModel().select(del-1);
				if(songlist.size()==1)
					{System.out.println("true");
					listView.getSelectionModel().select(0);}
				
				//WHEN SONG IS DELETED SELECTED CURSOR MOVES UP
		    	}
          }
          
          else if (option.get() == ButtonType.CANCEL) 
          {}
    }    
  
    
////////#############################################################
////////    
    
    public void closingsavePlaylist(BufferedWriter bufferedWriter) throws IOException
    {
    	int i=0;
    	while(i<songlist.size())
    	{
    		//int id = songlist.get(i);
        	try {
    		bufferedWriter.write(songdetail[i][0] + ":" +
        			songdetail[i][1] + ":" + 
        			songdetail[i][2] + ":" + 
        			songdetail[i][3]
        	);    		
			bufferedWriter.newLine();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			//e.printStackTrace();
    		}	
    		i++;
    	}
    	
    	
    }
    
   /* 
    public void makeaplaylist(BufferedWriter bufferedWriter,String abc) throws IOException
    {
    	//fileWriter = new FileWriter("stuff2.txt", true );  
        //bufferedWriter = new BufferedWriter(fileWriter);
    	System.out.println("INSIDE makeaplaylist()");
    	try {
        	bufferedWriter.write(abc + ":" + p2.getText() 
        	+ ":" + p3.getText() + ":" + p4.getText()
        	); //THIS STORES INFO IN DATA FILES AS 'DATA:DATA:DATA:DATA'
			bufferedWriter.newLine();
			System.out.println("should be there" + abc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}	
    	//bufferedWriter.close();
    	//fileWriter.close();
    }
   */
    
////////#############################################################
////////    
    //METHOD THAT READS THE PLAYLIST
    public void readPlaylist() throws FileNotFoundException
    {
        file = new File("stuff2.txt"); 
        Scanner readerPlaylist = new Scanner(file);       	
        int index = 0;
        int id=0;
        String first = "";
        while( readerPlaylist.hasNextLine() )
        {
        	Scanner rp2 = new Scanner( readerPlaylist.nextLine() ); 
        	rp2.useDelimiter(":");
        	while( rp2.hasNext() )
        	{
        		String abc = rp2.next();	
        		if(index==0)
        		{
	            	songlist.add(abc);

	        		Collections.sort(songlist, String.CASE_INSENSITIVE_ORDER);
	            	first = abc;
	          		obsList = FXCollections.observableArrayList(songlist);
	        		listView.setItems(obsList);  
	        		id = songlist.indexOf(abc);        		
	        		
	        		//THIS LITTLE THING IS JUST TO STORE NAME IN THE NAME FIELD
	        		String abc2 = "";
	        		int j=0;
	        		
	        		//THIS LITTLE SNIPPET ONLY CHECKS IF SONG HAS AN ARTIST
	        		//IF DONT THEN GOES TO ELSE, ITS CHECKING FOR THE '-' THAT SEPARATES IT
	        		if(!p2.getText().isEmpty())
	        		{
		        		while(abc.charAt(j)!='-')
		        		{
		        			abc2 = abc2+ abc.charAt(j);
		        			j++;
		        		}
	        		}
	        		else
	        		{abc2=abc;}
	        		
	        		songdetail[id][0]=abc2;
        		}
 
        		if(index==1)
        		{
        			songdetail[id][1]=abc;
        		}
        		
        		if(index==2)
        		{
        			songdetail[id][2]=abc;
        		}
        		
        		if(index==3)
        		{
        			songdetail[id][3]=abc;
        		}
        		index++;
        	}
        	index = 0;
            rp2.close();
        }
        readerPlaylist.close();
        count = songlist.size(); // THIS IS FOR WHEN THE LIST IS READ AND INDEX COUNT IS SET
    }

    
////////#############################################################
////////
    
	public void start(Stage mainStage) throws IOException
	{   p1.setVisible(false); 
		p2.setVisible(false); // So WHEN ADD IS CLICKED FORMS SHOW UP YEEHAA
    	p3.setVisible(false);
    	p4.setVisible(false);
      	l1.setVisible(false);

    	this.secondaryStage = mainStage;
    	
        if(file.isFile())
        {
        	if(file.canRead())
        	{
        		readPlaylist();
        	}
        }
    	
    	fileWriter = new FileWriter(file, false );  
        bufferedWriter = new BufferedWriter(fileWriter);

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
	    
	    //To terminate the application correctly
	    //NOTE : playlist saves when you close
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
			closingsavePlaylist(bufferedWriter);
			
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
		int id=  listView.getSelectionModel().getSelectedIndex() ;
		
		if(id<0) 
		{
		id=count;
			if(songlist.size()>=2)
			{
				obsList2 = FXCollections.observableArrayList(
						songdetail[id][0],
						songdetail[id][1],
						songdetail[id][2],
						songdetail[id][3]
						);
				detail.setItems(obsList2);
			}		
		}
		
		//If problem then delete ID>0 and also the select one
		else if(id>=0)
		{
			if(songlist.size()>=0) //if(songlist.size()>=2)
			{
			obsList2 = FXCollections.observableArrayList(
					songdetail[id][0],
					songdetail[id][1],
					songdetail[id][2],
					songdetail[id][3]
					);
			detail.setItems(obsList2);
			}
		}
		
		else
		{
			//System.out.println("$$$$zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz$$$$");
		}
	}
}
