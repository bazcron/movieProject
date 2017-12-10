package tester;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import models.User;

import static com.google.common.base.MoreObjects.toStringHelper;
import static models.Fixtures.users;

public class UserTest
{
	public String firstName,lastName,password,role,gender,occupation;
	public int age;
	public Long id;

  User homer = new User (6,"homer", "simpson","secret","default", 40, "m", "plumber");
  

  @Test
  public void testCreate()
  {
    assertEquals ("homer", homer.firstName);
    assertEquals ("simpson", homer.lastName);
    assertEquals("secret", homer.password);
    assertEquals("default", homer.role);
    assertEquals (40,   homer.age);   
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
    assertEquals ("models.User\n{\n  \\\"id\\\": 6,\\n\""
    	+ "\"firstName\": \"homer\",\n"
        + "  \"lastName\": \"simpson\",\n"
        + "  \"password\": \"secret\",\n"
        + "  \"role\": \"default\",\n"
        + "  \"age\": 40,\n"
        + "  \"gender\": \"male\"\n"
        + "  \"occupation\": \"plumber\"\n"
        + "}", homer.toString());
  }
 /* @Test
  public void testToString()
  {
    assertEquals ("User{" + " 6, homer, simpson, secret,default,40, m, plumber}", homer.toString());
  }*/
  
  @Test
  public void getUsers()
  {
	 //Collection<User> users = homer.getUsers();
	  
		// System.out.println(users);
  }
  
}