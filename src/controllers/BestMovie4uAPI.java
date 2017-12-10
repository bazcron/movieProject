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
import java.util.Optional;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Utils.FileLogger;
import Utils.Serializer;
import models.Movie;
import models.Rating;
	
public class BestMovie4uAPI {
	private Serializer serializer;
	private Map<Long, User> users = new HashMap<>();
	private Map<String, Movie> movieList = new HashMap<>();
	private Map<Long, Rating> ratingList = new HashMap<>();
	Optional<User> currentUser;

	
	public BestMovie4uAPI() {
	}
	
	public BestMovie4uAPI(Serializer MovieSerializer) {
		this.serializer = MovieSerializer;
	}
	
	// simplified login method
	  public boolean login(Long userName, String password) {
	    Optional<User> user = Optional.ofNullable(users.get(userName));
	    if (user.isPresent() && user.get().password.equals(password)) {
	      currentUser = user;
	      FileLogger.getLogger().log(currentUser.get().firstName+ " "+ currentUser.get().lastName + " is logged in...");
	      return true;
	    }
	    return false;
	  }
	  
	  // simplified logout method
	  public void logout() {
	    Optional<User> user = currentUser;
	    if (user.isPresent()) {
	      FileLogger.getLogger().log(currentUser.get().firstName + " "+ currentUser.get().lastName  +" has been logged out...");
	      currentUser = Optional.empty();
	    }
	  }
	
	@SuppressWarnings("unchecked")
	public void load() throws Exception{
		serializer.read();
		ratingList = (Map<Long, Rating>) serializer.pop();
		movieList = (Map<String, Movie>) serializer.pop();
		users = (Map<Long, User>) serializer.pop();
	}
	
	void store() throws Exception{
		serializer.push(users);
		serializer.push(movieList);
		serializer.push(ratingList);
		serializer.write();
	}
	
	//this is the initial addUser if reading from user.dat file if creating the xml file for the first time 
	public User addUser(long id, String firstName, String lastName,int age,String gender , String occupation){
		User user = new User (id,firstName, lastName, age,gender,occupation);
		users.put(id, user);
		return user;
	}
	//this addUser is used in Admin mode
	public User addUser(long id, String firstName, String lastName, String password, String role, int age,
			String gender, String occupation) {
		User user = new User (id,firstName, lastName, password,role,age,gender,occupation);
		users.put(id, user);
		return user;
		
	}
		
	public void deleteUser(long id){
		//users.remove(id);
		User user = users.remove(id);
	}
	
		
		
	public Movie addMovie(String id, String title, String year, String url){
			Movie movie = new Movie(id,title, year, url);
			movieList.put(id, movie);
			return movie;
		}
		
	public Rating addRating(long ratingCounter, String userID, String movieID, String rating){
			Rating rate = new Rating(ratingCounter,userID, movieID, rating);
			ratingList.put(ratingCounter, rate);
			return rate;
		}
		
	 public User getUser(Long id) 
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

	
	
}

