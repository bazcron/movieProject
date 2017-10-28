package models;

import static com.google.common.base.MoreObjects.toStringHelper;


public class Movie {

public String title, url;
public int year;
	
	public Movie(String title, int year, String url)
  {
    this.title = title;
    this.url = url;
    this.year = year;
    
  }
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(title)
							   .addValue(year)
                               .addValue(url)
                               .toString();
  }
  /*
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.title, this.year, this.url);  
  }
  */
}

