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
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import java.io.IOException;

public class Main implements ShellDependent {
	private static final String ADMIN = "admin";
	public BestMovie4uAPI bestMovie4uApi;
	private Shell theShell;

	
	 
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
			  	String anotherString = userData[0];
			  	int age = Integer.parseInt(thisString);
			  	long id = Long.parseLong(anotherString);
			  	bestMovie4uApi.addUser(id,userData[1],userData[2],age,userData[4],userData[5]);
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
			  	long ratingCounter = 01;
			  	while (scanner3.hasNextLine()) {
			  	ratingCounter++;   //increment counter by 1................
			  	String[] ratingData = scanner3.nextLine().split(delims);
			  	bestMovie4uApi.addRating(ratingCounter,ratingData[0],ratingData[1],ratingData[2]);
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
	
		public void cliSetShell(Shell theShell) {
		    this.theShell = theShell;
		  }

		
		 //....................................
		 @Command(description = "Log in")
		  public void logIn(@Param(name = "user name") Long userName, @Param(name = "password") String pass)
		      throws IOException {

		    if (bestMovie4uApi.login(userName, pass) && bestMovie4uApi.currentUser.isPresent()) {
		      User user = bestMovie4uApi.currentUser.get();
		      System.out.println("You are logged in as " + user.firstName +" " +user.lastName);
		      if (user.role!=null && user.role.equals(ADMIN)) {
		        AdminMenu adminMenu = new AdminMenu(bestMovie4uApi, user.firstName);
		        ShellFactory.createSubshell(user.firstName, theShell, "Admin", adminMenu).commandLoop();
		      } else {
		        DefaultMenu defaultMenu = new DefaultMenu(bestMovie4uApi, user);
		        ShellFactory.createSubshell(user.firstName, theShell, "Default", defaultMenu).commandLoop();
		      }
		    } else
		      System.out.println("Unknown username/password.");
		  }
		
	
public static void main(String[] args) throws Exception {
	Main main = new Main();
	Shell shell = ShellFactory.createConsoleShell("Hi Welcome to the Cliche Command Line, type '?list' to show All commands.", "",
	        main);
	shell.commandLoop();
	main.bestMovie4uApi.store();
		
	}
}

