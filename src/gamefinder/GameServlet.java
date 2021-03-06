package gamefinder;

import java.io.*;
import java.util.Calendar;
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
    	Email email = new Email(req.getUserPrincipal().getName());
    	boolean validGame = true;
    	
    	double latitude = 0;
	    double longitude = 0;
	    
        try{
  	       latitude = Double.parseDouble(req.getParameter("latitude"));
  	       longitude = Double.parseDouble(req.getParameter("longitude"));
         }
         catch(NumberFormatException e){
        	validGame = false;
         	_log.info("Error - no location picked");
      	   	resp.setContentType("text/html");
     		PrintWriter output = resp.getWriter();
      	    String res = "Error: no location chosen on map! You must click and or drag until a red marker appears!";
  		    output.println(
  		    "<!doctype html public \"-//w3c//dtd html 4.0 " +
  		    "transitional//en\">\n" +
  		    "<html>\n" +
  		    "<center>" + res + "<br><br><a href=\"/home.jsp\">Click to go back home</a></center>\n" +
  		    "</body></html>");
         	return;
         }
	    
        String sportName = req.getParameter("sport");
        String beginAMorPM = req.getParameter("beginAMorPM");
        String endAMorPM = req.getParameter("endAMorPM");
        //Long gameID = Long.parseLong(req.getParameter("id"));
        boolean subscribeUser = req.getParameter("emailNotification") != null;
        _log.info(req.getParameter("emailNotification"));
        int beginTimeHour = Integer.parseInt(req.getParameter("beginTimeHour"));
        int beginTimeMin = Integer.parseInt(req.getParameter("beginTimeMin"));
        String beginAMPM = req.getParameter("beginAMPM");
        String endAMPM = req.getParameter("endAMPM");
        int endTimeHour = Integer.parseInt(req.getParameter("endTimeHour"));
        int endTimeMin = Integer.parseInt(req.getParameter("endTimeMin"));
        int maxPlayers = Integer.parseInt(req.getParameter("numOfPlayers"));

    	int year = Integer.parseInt(req.getParameter("Year"));
    	int month = Integer.parseInt(req.getParameter("Month"));
    	int day = Integer.parseInt(req.getParameter("Day"));
        
        
        String locationName = req.getParameter("locationName");

    	//checking if the game date is before current date
    	Calendar current = Calendar.getInstance();
    	Calendar gameCal = Calendar.getInstance();
    	gameCal.set(year, month-1, day);
    	int beginTimeHour_24 = beginTimeHour;
    	if(beginTimeHour==12 && beginAMPM.equals("AM")){
    		beginTimeHour_24 = 0;
    		//why you playing at midnight??
    	}
    	else if(beginAMPM.equals("PM") && beginTimeHour!=12){
    		beginTimeHour_24 = 12+beginTimeHour;
    	}
        gameCal.set(Calendar.HOUR_OF_DAY, beginTimeHour_24);
        gameCal.set(Calendar.MINUTE, beginTimeMin);
        
        if(current.after(gameCal)){
        	validGame = false;
        	_log.info("Game is set to a previous date...");
        	_log.info("current time is "+current.getTime());
        	_log.info("game time is"+gameCal.getTime());

        	_log.info("Error: invalid game date");
      	   	resp.setContentType("text/html");
     		PrintWriter output = resp.getWriter();
      	    String res = "Error: invalid game date! Game was set to a previous date.";
  		    output.println(
  		    "<!doctype html public \"-//w3c//dtd html 4.0 " +
  		    "transitional//en\">\n" +
  		    "<html>\n" +
  		    "<center>" + res + "<br><br><a href=\"/home.jsp\">Click to go back home</a></center>\n" +
  		    "</body></html>");
         	return;
        }
        int endTimeHour_24=0;
        if(endTimeHour==12 && endAMPM.equals("AM")){
    		endTimeHour_24 = 0;
    	}
    	else if(endAMPM.equals("PM") && endTimeHour!=12){
    		endTimeHour_24 = 12+endTimeHour;
    	}
        endTimeHour_24=endTimeHour*60+endTimeMin;
        beginTimeHour_24=beginTimeHour*60+beginTimeMin;
        if(beginTimeHour_24 <= endTimeHour_24){
        	validGame = false;
        	_log.info("Error: invalid game duration");
      	   	resp.setContentType("text/html");
     		PrintWriter output = resp.getWriter();
      	    String res = "Error: invalid game duration! Game end time was set to before game begin time.";
  		    output.println(
  		    "<!doctype html public \"-//w3c//dtd html 4.0 " +
  		    "transitional//en\">\n" +
  		    "<html>\n" +
  		    "<center>" + res + "<br><br><a href=\"/home.jsp\">Click to go back home</a></center>\n" +
  		    "</body></html>");
         	return;
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
       
       game.setLocation(latitude, longitude);
       game.setLocationName(locationName);
       _log.info(locationName);

       if(subscribeUser){
    	   _log.info("subscribing user!");
    	   game.addEmail(email.getAddress());
       }
       else{
    	   _log.info("user didnt want to be subscribed?!");
       }
       
        resp.sendRedirect("/home.jsp");



       _log.info("Game from " + beginTimeHour +":" + beginTimeMin + " until " + endTimeHour + ":" + endTimeMin);
       if(validGame){
    	  ofy().save().entity(game);
    	  _log.info("New game created!!"); 
       }
       else{
    	   _log.info("Game not saved");
       }
    }
    @SuppressWarnings("rawtypes")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	// single person joins game and sends subscription
    	Email email = new Email(req.getUserPrincipal().getName());
        Long gameID = Long.parseLong(req.getParameter("gameId"));
        Game game= ofy().load().key(Key.create(Game.class,gameID)).get();
        game.setNumPlayers(game.getNumPlayers()+1);
        int count = game.getNumPlayers();
        if(count == game.getMaxPlayers())
        	game.sendEmails();
        else if(count > game.getMaxPlayers())
        	game.sendSingleEmail(email.getAddress());
        resp.sendRedirect("/home.jsp");
    }
    
}