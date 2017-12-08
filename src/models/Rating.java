package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class Rating {
	
	public String userId, movieId, rating;
	
	public Rating() {
		  
	  }
	
	public Rating(String userId, String movieId, String rating)
  {
    this.userId = userId;
    this.movieId = movieId;
    this.rating = rating;
    
  }
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(userId)
                               .addValue(movieId)
                               .addValue(rating)
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.userId, this.movieId, this.rating);  
  }
  
}

