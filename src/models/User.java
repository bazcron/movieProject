package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class User {
  public String firstName;
  public String lastName;
  public Long userId;
  public int age;
  public String password;
  public String role;
  public String gender;
  public String occupation;
  

  public User() {
	  
  }

  public User(long userId,String firstName, String lastName, int age, String gender, String occupation)
  {
    
    this.userId = userId;
	this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.gender = gender;
	this.occupation = occupation;
  }
  
  //overloaded the constructor
  public User(long userId,String firstName, String lastName, String password, String role, int age,String gender, String occupation) {
      this.userId = userId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
      this.gender= gender;
      this.occupation = occupation;
      this.password = password;
      this.role = role;
}
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(userId)
    						   .addValue(firstName)
                               .addValue(lastName)
                               .addValue(age)
                               .addValue(gender)
							   .addValue(occupation)
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.userId, this.firstName,  this.lastName, this.age, this.gender, this.occupation);  
  }
	
}
	

