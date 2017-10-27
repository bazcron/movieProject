package models;

import java.util.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

public class User {
	
  public String firstName;
  public String lastName;
  public int age;
  public String id;
  public String gender;
  public String occupation;

  public User() {
	  
  }

  public User(String id,String firstName, String lastName, int age, String gender, String occupation)
  {
    this.id = id;
	this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.gender = gender;
	this.occupation = occupation;
  }
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
    						   .addValue(firstName)
                               .addValue(lastName)
                               .addValue(age)
                               .addValue(gender)
							   .addValue(occupation)
                               .toString();
  }
  /*
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.firstName,  this.lastName, this.age, this.gender, this.occupation);  
  }
	*/
}
	

