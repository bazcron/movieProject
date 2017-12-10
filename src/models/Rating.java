package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class Rating {
	
	Long ratingCounter;
	public String userId, movieId, rating;
	static Long   counter = 0l;
	public Rating() {
		  
	  }
	
	public Rating(long ratingCounter, String userId, String movieId, String rating)
  {
	this.ratingCounter= ratingCounter;
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

