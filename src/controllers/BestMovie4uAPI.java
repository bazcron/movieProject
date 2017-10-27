package controllers;

	import models.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;



import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Utils.Serializer;
import Utils.XMLserializer;
import models.Movie;
import models.Rating;
	
public class BestMovie4uAPI {
	private Serializer serializer;
	private Map<String, User> users = new HashMap<>();
	//private List <User> users = new ArrayList<User>();
	
	public BestMovie4uAPI() {
	}
	
	public BestMovie4uAPI(Serializer MovieSerializer) {
		this.serializer = MovieSerializer;
	}
	
	@SuppressWarnings("unchecked")
	public void load() throws Exception{
		serializer.read();
		users = (Map<String, User>) serializer.pop();
	}
	
	void store() throws Exception{
		serializer.push(users);
		serializer.write();
	}
	
	 public Collection<User> getUsers ()
	  {
	    return users.values();
	  }
	
	public User addUser(String id, String firstName, String lastName,int age,String gender , String occupation){
		User user = new User (id,firstName, lastName, age,gender,occupation);
		users.put(id, user);
		return user;
	}
		
	public void removeUser(String id){
		users.remove(id);
	}
		
	public void	addMovie(String title, String year, String url){
			
		}
		
	public void	addRating(String userID, String movieID, int rating){
			
			
		}
		
	 public User getUser(String id) 
	  {
		 return users.get(id);
	  }
	 
	public void	getMovie(String movieID){
			
			
		}
		
	public void	getUserRatings(String userID){
			
			
		}
		
	public void	getMoviesByTitle(){
			
			
		}
		
	public void	getMoviesByYear(){ 
		
		}
		
	public void	initialLoad(Object csvFile){
			
		}
	
	
	@SuppressWarnings("unchecked")
	public void load(File file) throws Exception{
		ObjectInputStream is = null;
		try {
			XStream xstream = new XStream(new DomDriver());
			is = xstream.createObjectInputStream(new FileReader(file));
			//.....something here
			users= (Map<String, User>)is.readObject();
		}
		finally {
			if(is !=null) {
				is.close();
			}
		}
		
	}
	
	void store(File file) throws Exception
	  {
	    XStream xstream = new XStream(new DomDriver());
	    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
	    out.writeObject(users);
	    out.close(); 
	  }
			
				
		
	
}

