package controllers;

import java.util.Collection;
import java.util.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.Rating;
import models.User;

public class DefaultMenu {
	 private String name;
	  private User user;
	  private BestMovie4uAPI bestMovie4uApi;

	  public DefaultMenu(BestMovie4uAPI bestMovie4uApi, User user) {
	    this.bestMovie4uApi = bestMovie4uApi;
	    this.setName(user.firstName);
	    this.user = user;
	  }
	 
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	//Cliche Shell Commands............................
		 @Command(description="Get all Users details")
		  public void getUsers ()
		  {
		    Collection<User> users = bestMovie4uApi.getUsers();
		    System.out.println(users);
		  }
		 @Command(description="Get a Users details")
		  public void getUser (@Param(name="id") Long id)
		  {
		    User user = bestMovie4uApi.getUser(id);
		    System.out.println(user);
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
