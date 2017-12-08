package controllers;

	import models.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Utils.Serializer;
import models.Movie;
import models.Rating;
	
public class BestMovie4uAPI {
	private Serializer serializer;
	private Map<String, User> users = new HashMap<>();
	private Map<String, Movie> movieList = new HashMap<>();
	private Map<String, Rating> ratingList = new HashMap<>();
	
	public BestMovie4uAPI() {
	}
	
	public BestMovie4uAPI(Serializer MovieSerializer) {
		this.serializer = MovieSerializer;
	}
	
	@SuppressWarnings("unchecked")
	public void load() throws Exception{
		serializer.read();
		ratingList = (Map<String, Rating>) serializer.pop();
		movieList = (Map<String, Movie>) serializer.pop();
		users = (Map<String, User>) serializer.pop();
	}
	
	void store() throws Exception{
		serializer.push(users);
		serializer.push(movieList);
		serializer.push(ratingList);
		serializer.write();
	}
	
	public User addUser(String id, String firstName, String lastName,int age,String gender , String occupation){
		User user = new User (id,firstName, lastName, age,gender,occupation);
		users.put(id, user);
		return user;
	}
		
	public void deleteUser(String id){
		//users.remove(id);
		User user = users.remove(id);
	}
	
		
		
	public Movie addMovie(String id, String title, String year, String url){
			Movie movie = new Movie(id,title, year, url);
			movieList.put(id, movie);
			return movie;
		}
		
	public Rating addRating(String userID, String movieID, String rating){
			Rating rate = new Rating(userID, movieID, rating);
			ratingList.put(userID, rate);
			return rate;
		}
		
	 public User getUser(String id) 
	  {
		 return users.get(id);
	  }
	 
	 public Collection<User> getUsers(){
	    return users.values();
	  }
	 
	public  Collection<Movie> getMovies(){
		return movieList.values();	
		}
		
	public Collection<Rating> getUserRatings(){
		return ratingList.values();
		}
		
	public Movie getMoviesByTitle(String title){
			return movieList.get(title);
			
		}
		
	public void	getMoviesByYear(){ 
		
		}
		
	public void	initialLoad(Object csvFile){
			
		}
	
	
	
	
	/*@SuppressWarnings("unchecked")
	  void load(File file) throws Exception
	  {
	    ObjectInputStream is = null;
	    try
	    {
	      XStream xstream = new XStream(new DomDriver());
	      is = xstream.createObjectInputStream(new FileReader(file));
	      users = (Map<String, User>) is.readObject();
	      movieList = (Map<String, Movie>) is.readObject();
	      ratingList = (Map<String, Rating>) is.readObject();
	      
	    }
	    finally
	    {
	      if (is != null)
	      {
	        is.close();
	      }
	    }
	  }

	  public void store(File file) throws Exception
	  {
	    XStream xstream = new XStream(new DomDriver());
	    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
	    out.writeObject(users);
	    out.writeObject(movieList);
	    out.writeObject(ratingList);
	    out.close(); 
	  }

		*/
				
		
	
}

