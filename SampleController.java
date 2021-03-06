
package songlibview;

import javafx.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;
import javafx.scene.text.Text;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import stwolib.songlibstuff;

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
	
	static public ObservableList<String> obsList;
	static public  ObservableList<String> obsList2;
	int[][] code = new int[50][4];

	static ArrayList<String> songlist = new ArrayList<String>();
	//HashMap<Integer, String> songdetail = new HashMap<>(); // Integer is the index of the name
	static String[][] songdetail = new String [100][5];

	public Stage secondaryStage;
	
		
	static int count = 0;
	
	
///////##########################################3
/////
	
	//This method moves the array indexes to the right spots 
	
	
    public void addingPositionchange(int id)
    {
    	
    	//songlibstuff.addingPositionchange2(id);
    	
    	//songlibstuff.hello(id);
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
	
    public void checksame()
    {
    	String a = p1.getText();
    	String b = p2.getText();
    	
    	a=a.toLowerCase();
    	b=b.toLowerCase();
    	
		if( songlist.contains(a) )
		{
			//System.out.println("yes");
				//System.out.println("true2");
				songlibstuff.settingAlert();
		}	
    	
    	
    }
	
    @FXML
    private void addedname(ActionEvent event)
    {
    	checkcount++;
    	String abc =  p1.getText();
    	String artist = p2.getText();
    	int p=0;
    	
    	if( !abc.isEmpty() &&  !artist.isEmpty() )
    	{
    	abc = p1.getText() + "-" + p2.getText();
    	}
    	
    	if( p2.getText().isEmpty() && checkcount>2 )
    	{
    	//abc = p1.getText() + "-" + p2.getText();
    		songlibstuff.enterInfoError();
    	}
    	
    	if( p1.getText().isEmpty() && checkcount>2 )
    	{
    	//abc = p1.getText() + "-" + p2.getText();
    		songlibstuff.enterInfoError2();
    	}   
    	
    	if( !abc.isEmpty() && artist.isEmpty() )
    	{
    	abc = p1.getText();
    	}    	
    	
    	if(!abc.isEmpty() && !p2.getText().isEmpty() )
    	{
    		
    		int idforcheck1 = songlist.indexOf(p1.getText());

	    		if( songlist.contains(abc) )
	    		{
	    			int d = songlist.indexOf(abc);
	    			if(songlist.get(d).toLowerCase().equals(abc.toLowerCase()))
	    			{
	    				
	    			}
	    			//System.out.println("yes");
	    				//System.out.println("true2");
	    				songlibstuff.settingAlert();
	    		}

    		
	    		else
	    		{
	    			
	    			if( !songlist.contains(abc) )
		    		{
			    			boolean check11 = false;
			    			for(int i=0; i<songlist.size(); i++)
			    			{
				    			//int d = songlist.indexOf(abc);
				    			String wordinthelist = songlist.get(i);
				    			if(wordinthelist.toLowerCase().equals(abc.toLowerCase()))
				    			{
				    				//if in here, CONFLICT
				    				check11=true;
				    				break;
				    			}
				    			
			    			}
			    			
			    			if(check11) 
			    			{
			    				songlibstuff.settingAlert();
			    			}
			    			else {
			    				p=1;
			    			}
		    			}
	    			
		    			if(p==1)
		    			{
		    				p=0;
		    				System.out.println("in here");
			    			p=0;
				    		System.out.println("no");
				    		System.out.println( p1.getText() );
				    		songlist.add(abc);
				    		Collections.sort(songlist,String.CASE_INSENSITIVE_ORDER);
				    		int id2 = songlist.indexOf(abc);
				    		addingPositionchange(id2);
				    		detailadd(abc) ; 
		    			}
	    		}
    		
    	}
	      if (!abc.isEmpty()) 
	      { 
	    	  
	    	 // THIS PART UPDATES THE LIST WHEN NEW STUFF ADDED
	  		obsList = FXCollections.observableArrayList(songlist);
			listView.setItems(obsList); 
			int c = songlist.indexOf(abc);

			
			listView.getSelectionModel().select(c);
			
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

	static int checkcount=0;
////////#############################################################
////////
    //WHEN YOU PRESS THE ADD BUTTON YOU GET AN ALERT
    // 
    // THIS METHOD WORKS WHEN YOU CLICK THE ADD BUTTON
 	@FXML protected void adding(ActionEvent event) 
    {
    	//check++;
 		checkcount++;
 		p1.setVisible(true);
    	p2.setVisible(true);
    	p3.setVisible(true);
    	p4.setVisible(true);

    	addedname(event);
    	//System.out.println(count + " " + songlist); 
    }

//static int editCheck=0;	


////////#############################################################
////////

    @FXML protected void editing(ActionEvent event) 
    {
  		obsList = FXCollections.observableArrayList(songlist);
		listView.setItems(obsList);    


    	int id3 = listView.getSelectionModel().getSelectedIndex();

		listView.getSelectionModel().select(id3);
    	
	      Collections.sort(songlist, String.CASE_INSENSITIVE_ORDER);
      	l1.setVisible(true);
    	p1.setVisible(true);
    	p2.setVisible(true);
    	p3.setVisible(true);
    	p4.setVisible(true);
    	
    	int newid = 0;
    	//System.out.println(listView.getSelectionModel().getSelectedIndex());

    	int id = listView.getSelectionModel().getSelectedIndex();
    	listView.getSelectionModel().select(id);	
    	if(!p1.getText().isEmpty() || !p2.getText().isEmpty())
    	{
      	  Alert alert = new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Edit");
          alert.setHeaderText("Are you sure want to edit this song?");

          Optional<ButtonType> option = alert.showAndWait();
          String abc = p1.getText() + "-" + p2.getText();
          String firstW = p1.getText();
          
          
          if (option.get() == ButtonType.OK) 
          {     
        	  int idForTheCheck = songlist.indexOf(p1.getText());
              if( songlist.contains(abc)) // && songdetail[idForTheCheck][1]==p2.getText() )
              {  songlibstuff.settingAlert();}
              
              else if( p1.getText().isEmpty() || p2.getText().isEmpty()  )
              {
		    		songlibstuff.enterInfoError();  
              }
              
              else
              {
            		int p=0;
            	  if( !songlist.contains(abc) )
		    		{
			    			boolean check11 = false;
			    		
			    			for(int i=0; i<songlist.size(); i++)
			    			{
				    			//int d = songlist.indexOf(abc);
				    			String wordinthelist = songlist.get(i);
				    			if(wordinthelist.toLowerCase().equals(abc.toLowerCase()))
				    			{
				    				//if in here, CONFLICT
				    				check11=true;
				    				break;
				    			}
				    			
			    			}
			    			
			    			if(check11) 
			    			{
			    				songlibstuff.settingAlert();
			    			}
			    			else {
			    				p=1;
			    			}
		    		}
            	  if(p==1)
            	  {
            		  p=0;
        	   	abc = p1.getText() + "-" + p2.getText();
		    	songlist.set(id, abc);
        		Collections.sort(songlist, String.CASE_INSENSITIVE_ORDER);
		    	int idafteredit = songlist.indexOf(abc);
		    	newid = idafteredit;
		    	
		    	songdetail[id][0]=abc;
		    	//System.out.println(songlist);
		  		//obsList = FXCollections.observableArrayList(songlist);
				//listView.setItems(obsList); 
				
				if(!p2.getText().isEmpty())
				{
					abc = p2.getText();
					songdetail[id][1]=abc;
				}
				
				if(p2.getText().isEmpty())
				{
					
					//songdetail[id][1]="";
		    		songlibstuff.enterInfoError();
		    		
				}				
				
				if(!p3.getText().isEmpty())
				{
					abc = p3.getText();
					songdetail[id][2]=abc;
				}	
				
				if(p3.getText().isEmpty())
				{
					songdetail[id][2]="";
				}			
				
				if(!p4.getText().isEmpty())
				{
					abc = p4.getText();
					songdetail[id][3]=abc;
				}	
				
				if(p4.getText().isEmpty())
				{
					//abc = p4.getText();
					songdetail[id][3]= "";
				}
				
			      Collections.sort(songlist, String.CASE_INSENSITIVE_ORDER);
				    obsList = FXCollections.observableArrayList(songlist);
					listView.setItems(obsList); 
					
				
		    	if(idafteredit<id)
		    	{
		    		//THIS FORLOOP COPIES ONE VALUE+ TO THE NEXT INDEX 
		    		//LEAVING THE SORTED PLACE WHERE THE EDITED PART GOES
		    		// THE STATEMENTS OUTSIDE OF THE FORLOOP BASICALLY 
		    		//TAKES CARE OF THE POSITIONS THAT ARE NOT TAKEN CARE OF
			    	for(int i=id-1; i>idafteredit ;i--)
			    	{
			    		
		    			songdetail[i+1][0] = songdetail[i][0];
		    			songdetail[i+1][1] = songdetail[i][1];
		    			songdetail[i+1][2] = songdetail[i][2];
		    			songdetail[i+1][3] = songdetail[i][3];

			    	}
			    	songdetail[idafteredit+1][0] = songdetail[idafteredit][0];
			    	songdetail[idafteredit+1][1] = songdetail[idafteredit][1];
			    	songdetail[idafteredit+1][2] = songdetail[idafteredit][2];
			    	songdetail[idafteredit+1][3] = songdetail[idafteredit][3];	
					songdetail[idafteredit][0] = p1.getText();
					songdetail[idafteredit][1] = p2.getText();
					songdetail[idafteredit][2] = p3.getText();
					songdetail[idafteredit][3] = p4.getText();
		    	}
		    	
		    	if(idafteredit>id)
		    	{
		    		//THIS FORLOOP COPIES ONE VALUE+ TO THE NEXT INDEX 
		    		//LEAVING THE SORTED PLACE WHERE THE EDITED PART GOES
		    		// THE STATEMENTS OUTSIDE OF THE FORLOOP BASICALLY 
		    		//TAKES CARE OF THE POSITIONS THAT ARE NOT TAKEN CARE OF
			    	if(id==0)
			    	{
			    		for(int i=id; i<idafteredit ;i++)
				    	{
				    		if(i==0)
				    		{}
				    		else
				    		{
				    			songdetail[i-1][0] = songdetail[i][0];
				    			songdetail[i-1][1] = songdetail[i][1];
				    			songdetail[i-1][2] = songdetail[i][2];
				    			songdetail[i-1][3] = songdetail[i][3];
				    		}
				    	}
				    	songdetail[idafteredit-1][0] = songdetail[idafteredit][0];
				    	songdetail[idafteredit-1][1] = songdetail[idafteredit][1];
				    	songdetail[idafteredit-1][2] = songdetail[idafteredit][2];
				    	songdetail[idafteredit-1][3] = songdetail[idafteredit][3];	
						songdetail[idafteredit][0] = p1.getText();
						songdetail[idafteredit][1] = p2.getText();
						songdetail[idafteredit][2] = p3.getText();
						songdetail[idafteredit][3] = p4.getText();			    		
			    	}
			    	
			    	else
			    	{
			    		for(int i=id; i<idafteredit ;i++)
				    	{
				    		
			    			songdetail[i-1][0] = songdetail[i][0];
			    			songdetail[i-1][1] = songdetail[i][1];
			    			songdetail[i-1][2] = songdetail[i][2];
			    			songdetail[i-1][3] = songdetail[i][3];
				    	}
				    	songdetail[idafteredit-1][0] = songdetail[idafteredit][0];
				    	songdetail[idafteredit-1][1] = songdetail[idafteredit][1];
				    	songdetail[idafteredit-1][2] = songdetail[idafteredit][2];
				    	songdetail[idafteredit-1][3] = songdetail[idafteredit][3];	
						songdetail[idafteredit][0] = p1.getText();
						songdetail[idafteredit][1] = p2.getText();
						songdetail[idafteredit][2] = p3.getText();
						songdetail[idafteredit][3] = p4.getText();
			    	}
		    	}		    	
		    	
            	  }
          	}
          //end of else
          }
          
    	}


	      p1.clear();
	      p2.clear();
	      p3.clear();
	      p4.clear();
	      

	      Collections.sort(songlist, String.CASE_INSENSITIVE_ORDER);


	    obsList = FXCollections.observableArrayList(songlist);
		listView.setItems(obsList); 

		listView.getSelectionModel().select(newid);		

		
    }
    
    public void changeAfterEdit(String wored,int idbefore,int idafteredit)
    {
    	if(idafteredit<idbefore)
    	{
    		for(int i=idafteredit ; i<=idbefore ; i++)
    		{
    			songdetail[i][0] = songdetail[i+1][0];
    			songdetail[i][1] = songdetail[i+1][1];
    			songdetail[i][2] = songdetail[i+1][2];
    			songdetail[i][3] = songdetail[i+1][3];
    		}

    	}
    	//datachangeAfterDelete(d);
    }
	
	
////////#############################################################
////////  
 
    public void datachangeAfterEdit(int id)
    {
    	for(int i=id; i<songlist.size(); i++)
    	{
        	songdetail[i+1][0] = songdetail[i][0];
        	songdetail[i+1][1] = songdetail[i][1];    	
        	songdetail[i+1][2] = songdetail[i][2];      	
        	songdetail[i+1][2] = songdetail[i][2];     		
    	}
    }    
    
    public void datachangeAfterDelete(int id)
    {
    	for(int i=id; i<songlist.size(); i++)
    	{
        	songdetail[i][0] = songdetail[i+1][0];
        	songdetail[i][1] = songdetail[i+1][1];    	
        	songdetail[i][2] = songdetail[i+1][2];      	
        	songdetail[i][2] = songdetail[i+1][2];     		
    	}
    }
    
    
    @FXML protected void deleting(ActionEvent event) 
    {
    	  Alert alert;//= new Alert(AlertType.CONFIRMATION);
          //alert.setTitle("Remove");
          //alert.setHeaderText("Are you sure want to delete this song?");
    	int confirmDel = songlibstuff.settingAlertDel();
    	
          //Optional<ButtonType> option = alert.showAndWait();
          
          if (confirmDel == 1) 
          { 	
		    	int del = listView.getSelectionModel().getSelectedIndex();

		    	int sized = listView.getItems().size();
		    	
		    	if(del>=0)
		    	{

		    	songlist.remove(del);
		    	datachangeAfterDelete(del);
		    	//THIS PART UPDATES THE LIST VIEW
		  		obsList = FXCollections.observableArrayList(songlist);
				listView.setItems(obsList);    
				count--;
				listView.getSelectionModel().select(del);
				
				if((del+1)==sized)
				{
					listView.getSelectionModel().select(del-1);
				}
				
				if(songlist.size()==1)
					{
										listView.getSelectionModel().select(0);}
				
				//WHEN SONG IS DELETED SELECTED CURSOR MOVES UP
		    	}
          }
          
          else if (confirmDel ==  0) 
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
		}
	}
}
