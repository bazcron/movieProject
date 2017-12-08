package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;


public class Movie {

public String title, url;
public String year, id;

public Movie() {
	
}
	
	public Movie(String id, String title, String year, String url)
  {
	this.id = id;
    this.title = title;
    this.url = url;
    this.year = year;
  }
  
@Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
    						. addValue(title)
							   .addValue(year)
                               .addValue(url)
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.title, this.year, this.url);  
  }

  

}

