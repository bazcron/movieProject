package tester;
import controllers.BestMovie4uAPI;
import static org.junit.Assert.*;

import java.io.File;

import models.Rating;
import models.Movie;
import models.User;

import org.junit.Test;

import Utils.Serializer;
import Utils.XMLserializer;
import static models.Fixtures.users;
import static models.Fixtures.movieList;
import static models.Fixtures.ratingList;

public class PersistenceTest
{
 /* BestMovie4uAPI bestMovie4u;
  
  @Test
  public void testXMLSerializer() throws Exception
  { 
    String datastoreFile = "testdatastore.xml";
    //deleteFile (datastoreFile);

    Serializer serializer = new XMLserializer(new File (datastoreFile));

    bestMovie4u = new BestMovie4uAPI(serializer); 
    populate(bestMovie4u);
    bestMovie4u.store();

    BestMovie4uAPI bestMovie4u2 =  new BestMovie4uAPI(serializer);
    bestMovie4u2.load();

    assertEquals (bestMovie4u.getUsers().size(), bestMovie4u2.getUsers().size());
    for (User user : bestMovie4u.getUsers())
    {
      assertTrue (bestMovie4u2.getUsers().contains(user));
    }
    //deleteFile ("testdatastore.xml");
  }*/
  
  
}