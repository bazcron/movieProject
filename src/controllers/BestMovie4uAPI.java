package controllers;

import models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

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
		users.remove(id);
		//User user = users.remove(id);
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
	/*public void topTenMovies(){
		ArrayList movieId = new ArrayList();
		ArrayList movieRatings = new ArrayList();
		ArrayList movieNumOfRatings = new ArrayList();
		
		Set<Long> keys = ratingList.keySet(); //using keySet to step through the hashmap
        for(Long key: keys){
        	String movieValue = ratingList.get(key).movieId;
        	String ratingValue = ratingList.get(key).rating;
        	int movieNum = Integer.parseInt(movieValue);
        	int ratingNum = Integer.parseInt(ratingValue);
        	
        	movieId.set(movieNum, movieNum);
        	int addValue = (int) movieRatings.get(movieNum);
        	addValue+=ratingNum;
        	movieRatings.set(movieNum, addValue);
        	int incValues = (int) movieNumOfRatings.get(movieNum);
        	incValues++;
        	movieNumOfRatings.set(movieNum, incValues);
        }
	}*/
	public <T> void sortMovies(){
		
	}
	 public User getUser(Long id) 
	  {
		 return users.get(id);
	  }
	 
	public Movie getMovie(String title) 
	  {
		 Set<String> keys = movieList.keySet(); //using keySet to step through the hashmap
	        for(String key: keys){
	            if (movieList.get(key).title.contains(title)) {  //if title is in the movie list
	       	     System.out.println("Here is your Movie Information...");  
	            return movieList.get(key);
	            }
	        }
	     System.out.println("Sorry that title is not in our Database");  
		 return null;  
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

