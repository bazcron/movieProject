package controllers;

import java.util.Collection;
import java.util.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.Rating;
import models.User;

public class AdminMenu {
	 private String name;
	 private BestMovie4uAPI bestMovie4uApi;

	  public AdminMenu(BestMovie4uAPI bestMovie4uApi, String userName) {

	    this.bestMovie4uApi = bestMovie4uApi;
	    this.setName(userName);
	  }

	  
	 @Command(description="Create a new User (id,firstName, lastName, password, role, age,gender, occupation")
	  public void addUser (@Param(name="id") long id, @Param(name="first name") String firstName, @Param(name="last name") String lastName, 
			  @Param(name="password") String password, @Param(name="role") String role,               
			  @Param(name="age") int age, @Param(name="gender") String gender, @Param(name="occupation") String occupation)
	  {
	    bestMovie4uApi.addUser(id, firstName, lastName, password,role,age, gender, occupation);
	  }
	 public String getName() {
		    return name;
		  }
	 public void setName(String name) {
		    this.name = name;
		  }
	 @Command(description="Get all User details")
	  public void getUsers ()
	  {
	    Collection<User> users = bestMovie4uApi.getUsers();
	    System.out.println(users);
	  }
	 @Command(description="Get a Users detail")
	  public void getUser (@Param(name="id") Long id)
	  {
	    User user = bestMovie4uApi.getUser(id);
	    System.out.println(id);  //...................take out
	    System.out.println(user);
	  }
	 @Command(description="Delete a User")
	  public void deleteUser (@Param(name="id") Long id)
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

}
