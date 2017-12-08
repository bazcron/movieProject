package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Utils.FileLogger;
import Utils.Serializer;
import Utils.XMLserializer;
import models.User;
import models.Movie;
import models.Rating;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.ShellFactory;
import java.io.IOException;

public class Main {
	public static BestMovie4uAPI bestMovie4uApi;
	
	//Cliche Shell Commands............................
	 @Command(description="Get all User details")
	  public void getUsers ()
	  {
	    Collection<User> users = bestMovie4uApi.getUsers();
	    System.out.println(users);
	  }
	 @Command(description="Create a new User (id, first name, last name, age, gender, occupation")
	  public void addUser (@Param(name="id") String id, @Param(name="first name") String firstName, @Param(name="last name") String lastName, 
	                          @Param(name="age") int age, @Param(name="gender") String gender, @Param(name="occupation") String occupation)
	  {
	    bestMovie4uApi.addUser(id, firstName, lastName, age, gender, occupation);
	  }
	 @Command(description="Get a Users detail")
	  public void getUser (@Param(name="id") String id)
	  {
	    User user = bestMovie4uApi.getUser(id);
	    System.out.println(user);
	  }
	 @Command(description="Delete a User")
	  public void deleteUser (@Param(name="id") String id)
	  {
	    Optional<User> user = Optional.ofNullable(bestMovie4uApi.getUser(id));
	    if (user.isPresent())
	    {
	    	bestMovie4uApi.deleteUser(user.get().id);
	    }
	  }
	 @Command(description="Get all Movie details")
	  public void getMovies ()
	  {
	    Collection<Movie> moviesList = bestMovie4uApi.getMovies();
	    System.out.println(moviesList);
	  }
	 @Command(description="Get all Rating details")
	  public void getRating ()
	  {
	    Collection<Rating> ratingList = bestMovie4uApi.getUserRatings();
	    System.out.println(ratingList);
	  }
	 
	 
	public Main() throws Exception{
		File movieInfo = new File("movieInfo.xml");	
		Serializer serializer = new XMLserializer(movieInfo);
		bestMovie4uApi = new BestMovie4uAPI(serializer);
		if(movieInfo.isFile()) {
			System.out.println("Loading Data from file");
					bestMovie4uApi.load();
					//display all data from xml file...............
			}else { //if file does not exist, read in the data and create it
				FileLogger logger = FileLogger.getLogger();
			    logger.log("Creating user list");
			    
			    //reads the users from a file
			    String delims = "[|]";
			  	Scanner scanner = new Scanner(new File("users5.dat"));
			  	while (scanner.hasNextLine()) {
			  	String[] userData = scanner.nextLine().split(delims);
			  	String thisString = userData[3];
			  	int age = Integer.parseInt(thisString);
			  	bestMovie4uApi.addUser(userData[0],userData[1],userData[2],age,userData[4],userData[5]);
			  	}
			  	scanner.close();
			  	
			  	//read the movies...
			  	Scanner scanner2 = new Scanner(new File("items5.dat"));
			  	while (scanner2.hasNextLine()) {
			  	String[] movieData = scanner2.nextLine().split(delims);
			  	bestMovie4uApi.addMovie(movieData[0],movieData[1],movieData[2],movieData[3]);
			  	}
			  	scanner2.close();
			  	
			  	//read the ratings...
			  	Scanner scanner3 = new Scanner(new File("ratings5.dat"));
			  	while (scanner3.hasNextLine()) {
			  	String[] ratingData = scanner3.nextLine().split(delims);
			  	bestMovie4uApi.addRating(ratingData[0],ratingData[1],ratingData[2]);
			  	}
			  	scanner3.close();
			    
			    logger.log("Serializing contacts to XML");
			    XStream xstream = new XStream(new DomDriver());
			    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("movieInfo.xml"));
			    out.writeObject(bestMovie4uApi);
			    out.close();    

			    logger.log("Finished - shutting down");
			    
			    bestMovie4uApi.store();
				}
		}//...........end of Main()
	
public static void main(String[] args) throws Exception {
	Main main = new Main();
		ShellFactory.createConsoleShell("Hi Welcome to the Cliche Command Line, type ?list to show All commands.", "", new Main())
        .commandLoop(); // and three.
		
		main.bestMovie4uApi.store();
		

	
	 /*
	 //this deletes the last user from the Get method, then displays the list again
	bestMovie4uApi.removeUser(checkUser.id);
	users = bestMovie4uApi.getUsers();
	System.out.println(users);
	*/
	 
	/* try {
		bestMovie4uApi.store(new File("movieInfo.xml"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	}
}

