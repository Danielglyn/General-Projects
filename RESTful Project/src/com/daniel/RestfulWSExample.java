package com.daniel;

import com.daniel.model.Actor;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by dgly0001 on 23/07/2015.
 */
public class RestfulWSExample {
  private static final String api_version = "1.01A rev.18729";
  static Logger logger = Logger.getLogger(String.valueOf(RestfulWSExample.class));
  static String xmlString = null;
  private static Map<String, Actor> actors = new HashMap<String, Actor>();

  static {
    System.out.println("Initializing Internal DataStore...");
    actors.put("123", new Actor(123, "Hugh Jackson", "Hugh Michael Jackman", "October 12, 1968", "hughjackman@mail.com", "https://stocklandmartelblog.files.wordpress.com/2013/07/nino-muncc83oz_hugh-jackman_page_3.jpg", true));
    actors.put("777", new Actor(777, "Chris Evans", "Christopher Robert Evans", "June 13, 1981", "chris.evans@comcast.com", "http://assets-s3.usmagazine.com/uploads/assets/celebrities/28454-chris-evans/1311353993_chris-evans-bio-402.jpg", true));
    actors.put("654", new Actor(654, "Robert Downey Jr.", "Robert John Downey Jr", "April 4, 1965", "robertdowney@verizon.com", "http://thehollywoodbillboard.com/wp-content/uploads/2014/10/robert-downey-jr-iron-man-beard-background-1.jpg", true));
    actors.put("255", new Actor(255, "Johnny Depp", "John Christopher Depp II", "June 9, 1963", "johndepp@hollywood.com", "http://images.latinpost.com/data/images/full/9536/johnny-depp-at-transcendence-los-angeles-premiere.jpg?w=600", true));
  }

  @Path("/version")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String returnVersion(){
    return "<p>Version: " + api_version + "</p>";
  }

  // Default @Path
  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public ArrayList<Actor> getAllActors(){
    System.out.println("Getting All Actors...");
    ArrayList<Actor> actorList = new ArrayList<Actor>(actors.values());
    return actorList;
  }

  @Path("{id}")
  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public Actor getActorByID(@PathParam("id") String id){
    System.out.println("Getting actor by id: " + id);
    Actor actor = getActorByID(id);

    if (actor != null) {
      logger.info("Inside getActorById, returned: " + actor.toString());
    }
    else
      logger.info("Inside getActorById, ID: " + id + ", not found.");
    return actor;
  }

  @Path("{id}")
  @PUT
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public Actor updateActor(Actor actor){
    actors.put(""+actor.getId(), actor);
    if (actor != null) {
      logger.info("Inside updateActor, returned: " + actor.toString());
    }
    else
      logger.info("Inside updateActor, ID: " + actor.getId() + ", not found.");
    return actor;
  }

  @Path("/search/{query}")
  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public ArrayList<Actor> searchActorByName(@PathParam("query") String query) {
    System.out.println("Searching actor by Name: " + query);

    ArrayList<Actor> actorList = new ArrayList<Actor>();

    for (Actor actorName: actors.values()){
      if(actorName.getName().toLowerCase().contains(query.toLowerCase())){
        actorList.add(actorName);
      }
    }

    return actorList;
  }

  @Path("/add")
  @GET
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public Actor addActor(Actor actor){
    System.out.println("Adding actor with ID: " + actor.getId());

    if (actor != null) {
      logger.info("Inside addActor, returned: " + actor.toString());
      actors.put(""+actor.getId(), actor);
      System.out.println("Number of actors now: " + actors.size());
      System.out.println("Current list of actors: " + actors);
    }
    else
      logger.info("Inside addActor, unable to add actors...");

    return actor;
  }

  @Path("{id}")
  @DELETE
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public Actor deleteActorById(@PathParam("id") String id) {
    System.out.println("Removing actor with ID: " + id);
    Actor actor = getActorByID(id);

    if (actor != null) {
      logger.info("Inside deleteActorById, returned: " + actor.toString());
    } else
      logger.info("Inside deleteActorById, ID: " + id + ", NOT FOUND!");

    return actor;
  }

}
