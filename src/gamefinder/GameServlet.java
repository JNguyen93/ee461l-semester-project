package gamefinder;

import java.io.*;
import java.util.logging.Logger;

import javax.servlet.http.*;

import gamefinder.GameServlet;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class GameServlet extends HttpServlet {

	
	static {
        ObjectifyService.register(Game.class);
       
    }

	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
    @SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    	double latitude = 0;
	    double longitude = 0;
        String sportName = req.getParameter("sport");
        //String beginTime = req.getParameter("beginTime");
        String beginAMorPM = req.getParameter("beginAMorPM");
        String endAMorPM = req.getParameter("endAMorPM");
        //String endTime = req.getParameter("endTime");

        //Long gameID = Long.parseLong(req.getParameter("id"));
      

        String email = req.getParameter("email");
        //String sms = req.getParameter("sms");

        int beginTimeHour = Integer.parseInt(req.getParameter("beginTimeHour"));
        int beginTimeMin = Integer.parseInt(req.getParameter("beginTimeMin"));
        String beginAMPM = req.getParameter("beginAMPM");
        String endAMPM = req.getParameter("endAMPM");
        //int gameIndex = Integer.parseInt(req.getParameter("index"));
        int endTimeHour = Integer.parseInt(req.getParameter("endTimeHour"));
        int endTimeMin = Integer.parseInt(req.getParameter("endTimeMin"));
        int maxPlayers = Integer.parseInt(req.getParameter("numOfPlayers"));
        Boolean emailAlert = Boolean.parseBoolean(email);
        //Boolean smsAlert = Boolean.parseBoolean(sms);
        int year = Integer.parseInt(req.getParameter("Year"));
        int month = Integer.parseInt(req.getParameter("Month"));
        int day = Integer.parseInt(req.getParameter("Day"));
        String locationName = req.getParameter("locationName");

        try{
 	       latitude = Double.parseDouble(req.getParameter("latitude"));
 	       longitude = Double.parseDouble(req.getParameter("longitude"));
        }
        catch(NumberFormatException e){
        	_log.info("Error - no location picked");
     	   	resp.setContentType("text/html");
    		PrintWriter output = resp.getWriter();
     	    String res = "Error: no location chosen on map!";
 		    output.println(
 		    "<!doctype html public \"-//w3c//dtd html 4.0 " +
 		    "transitional//en\">\n" +
 		    "<html>\n" +
 		    "<center>" + res + "</center>\n" +
 		    "</body></html>");
        	resp.sendRedirect("/home.jsp");
        }

       Game game = new Game();
       game.setSport(sportName);
       game.setNumPlayers(1);
       game.setMaxPlayers(maxPlayers);
       game.setStartTime(beginTimeHour, beginTimeMin, beginAMPM);
       game.setEndTime(endTimeHour, endTimeMin, endAMPM);
       game.setYear(year);
       game.setMonth(month);
       game.setDay(day);
       game.setEmailAlerts(emailAlert);
       
       game.setLocation(latitude, longitude);
       game.setLocationName(locationName);
       _log.info(locationName);

        resp.sendRedirect("/home.jsp");

       ofy().save().entity(game);

       _log.info("Game from " + beginTimeHour +":" + beginTimeMin + " until " + endTimeHour + ":" + endTimeMin);

        _log.info("New game created!");
    }
    @SuppressWarnings("rawtypes")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    
          Long gameID = Long.parseLong(req.getParameter("gameId"));
          //_log.info(Long.toString(gameID));
          Game game= ofy().load().key(Key.create(Game.class,gameID)).get();
          game.setNumPlayers(game.getNumPlayers()+1);
          resp.sendRedirect("/joingame.jsp");
    }
    
}