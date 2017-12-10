package models;

public class Fixtures
{
  public static User[] users =
  {
    new User (1,"marge", "simpson", "marge@simpson.com", "default", 30, "this", "secret"),
    new User (2,"lisa",  "simpson", "lisa@simpson.com",  "default", 8, "this",  "secret"),
    new User (3,"bart",  "simpson", "bart@simpson.com",  "default", 12, "this", "secret"),
    new User (4,"maggie","simpson", "maggie@simpson.com", "default", 3, "this", "secret")
  };
  
  public static Movie[] movieList =
	  {
	    new Movie ("marge", "simpson", "marge@simpson.com", "this"),
	    new Movie ("lisa",  "simpson", "lisa@simpson.com",  "this"),
	    new Movie ("bart",  "simpson", "bart@simpson.com",  "this"),
	    new Movie ("maggie","simpson", "maggie@simpson.com", "this"),
	    
	  };
  public static Rating[] ratingList =
	  {
		  new Rating (1,"marge", "simpson", "marge@simpson.com"),
		   new Rating (2,"lisa",  "simpson", "lisa@simpson.com"),
		    new Rating (3,"bart",  "simpson", "bart@simpson.com"),
		    new Rating (4,"maggie","simpson", "maggie@simpson.com"),
	  };
  
}
