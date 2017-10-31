package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Utils.FileLogger;
import Utils.Serializer;
import Utils.XMLserializer;
import models.User;
import models.Movie;
import models.Rating;

public class Main {

	public static void main(String[] args) throws IOException {
	
		File movieInfo = new File("movieInfo.xml");	
		Serializer serializer = new XMLserializer(movieInfo);
		BestMovie4uAPI bestMovie4uApi = new BestMovie4uAPI(serializer);
		
	//checks to see if movieInfo.xml already exists, id it doesn't, it reads in the data and creates movieInfo.xml
	try {
		if(movieInfo.exists()) {
			System.out.println("XML file already exists");
		}else { //if file does not exist, read in the data and create it
			FileLogger logger = FileLogger.getLogger();
		    logger.log("Creating user list");
		    
		  //reads the users from a file
		  	String delims = "[|]";
		  	Scanner scanner = new Scanner(new File("users5.dat"));
		  	while (scanner.hasNextLine()) {
		  	String[] data = scanner.nextLine().split(delims);
		  	String thisString = data[3];
		  	int age = Integer.parseInt(thisString);
		  	bestMovie4uApi.addUser(data[0],data[1],data[2],age,data[4],data[5]);
		  	}
		  	scanner.close();
		    
		    logger.log("Serializing contacts to XML");
		    XStream xstream = new XStream(new DomDriver());
		    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("movieInfo.xml"));
		    out.writeObject(bestMovie4uApi);
		    out.close();    

		    logger.log("Finished - shutting down");
			}
		}
		catch (IOException e) {
			System.out.println("Data read from source data and XML file created");
		}
	
	
	
	
	
	
	
	
	/*
	User testUser = bestMovie4uApi.getUser("1");
	bestMovie4uApi.addUser(testUser.id, "firstName", "lastName", 2, "gender", "occupation");
	try {
		bestMovie4uApi.store();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	 Collection<User> users = bestMovie4uApi.getUsers();
	 System.out.println(users);
	
	 
	 //this gets a User by id number and displays it
	User checkUser = bestMovie4uApi.getUser("2");
	 System.out.println(checkUser);
	/*
	 //this deletes the last user from the Get method, then displays the list again
	bestMovie4uApi.removeUser(checkUser.id);
	users = bestMovie4uApi.getUsers();
	System.out.println(users);
	*/
	 
	/*
	XStream xstream = new XStream(new DomDriver());
    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("movieInfo.xml"));
    out.writeObject(users);
    out.close();
	*/
	
	 try {
		bestMovie4uApi.store();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}

