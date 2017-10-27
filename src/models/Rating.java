package models;

import java.util.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

public class Rating {
	
	public int userId, movieId, rating;
	
	public Rating(int userId, int movieId, int rating)
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
  /*
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.userId, this.movieId, this.rating);  
  }
  */
}

