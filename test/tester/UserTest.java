package tester;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import models.User;

import static models.Fixtures.users;

public class UserTest
{
  User homer = new User ("6","homer", "simpson", 5, "m", "plumber");

  @Test
  public void testCreate()
  {
	assertEquals ("6", homer.id);
    assertEquals ("homer", homer.firstName);
    assertEquals ("simpson", homer.lastName);
    assertEquals (5,   homer.age);   
    assertEquals ("m", homer.gender);
    assertEquals ("plumber", homer.occupation);
  }
/*
  @Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    for (User user : users)
    {
      ids.add(user.id);
    }
    assertEquals (users.length, ids.size());
  }
*/
  @Test
  public void testToString()
  {
    assertEquals ("User{" + " 6, homer, simpson, 5, m, plumber}", homer.toString());
  }
  
  @Test
  public void getUsers()
  {
	 // Collection<User> users = bestMovie4uApi.getUsers();
	  
		 System.out.println(users);
  }
  
}