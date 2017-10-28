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
	if(movieInfo.isFile()) {
		try {
			bestMovie4uApi.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
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
	*/
	
	User testUser = bestMovie4uApi.getUser("1");
	bestMovie4uApi.addUser(testUser.id, "firstName", "lastName", 2, "gender", "occupation");
	try {
		bestMovie4uApi.store();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
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
	}
	*/

}
}
