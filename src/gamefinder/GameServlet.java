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

    //    UserService userService = UserServiceFactory.getUserService();
//        User user = userService.getCurrentUser();
    	double latitude2 = 0;
	    double longitude2 = 0;
        String sportName = req.getParameter("sport");
        //String beginTime = req.getParameter("beginTime");
        String beginAMorPM = req.getParameter("beginAMorPM");
        String endAMorPM = req.getParameter("endAMorPM");
        //String endTime = req.getParameter("endTime");

        //Long gameID = Long.parseLong(req.getParameter("id"));
      

        String email = req.getParameter("email");
        //String sms = req.getParameter("sms");

        //String test = req.getParameter("endTimeMin");
        //_log.info(sportName);
        

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
        
        //HOW TO GET LOCATION WTF
        
//      String longitude = req.getParameter("longitude");
//      String latitude = req.getParameter("latitude");
        //_log.info(longitude +" "+ latitude);

        try{
 	       latitude2 = Double.parseDouble(req.getParameter("latitude"));
 	       longitude2 = Double.parseDouble(req.getParameter("longitude"));
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

       game.setEmailAlerts(emailAlert);
       //game.setSmsAlerts(smsAlert);

       
//       
//       // saving Location
//       String longitude1 = req.getParameter("longitude");
//       String latitude1 = req.getParameter("latitude");
//       _log.info(longitude1 +" "+ latitude1);
//       
//       double latitude = Double.parseDouble(req.getParameter("latitude"));
//       double longitude = Double.parseDouble(req.getParameter("longitude"));
//       game.setLocation(latitude, longitude);
//
//       // TODO: getting location address
//       
//       // saving locationName
//       String locationName = req.getParameter("locationName");
//       game.setLocationName(locationName);
//       _log.info(locationName);
       
       game.setLocation(latitude2, longitude2);

       // TODO: getting location address
       
       // saving locationName
       String locationName = req.getParameter("locationName");
       game.setLocationName(locationName);
       _log.info(locationName);
       
       if(beginAMorPM=="am"){
    	//   game.setDate(beginTime);
       }
       else if(beginAMorPM=="pm"){
    	 //  game.setDate(beginTime+12);
       }
       if(endAMorPM=="am"){
       	//   game.setDate(endTime);
          }
          else if(endAMorPM=="pm"){
       	 //  game.setDate(endTime+12);
          }

        resp.sendRedirect("/home.jsp");

       ofy().save().entity(game);
       //resp.sendRedirect("/");
       _log.info("Game from " + beginTimeHour +":" + beginTimeMin + " until " + endTimeHour + ":" + endTimeMin);

        _log.info("New game created!");
    }
    @SuppressWarnings("rawtypes")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    
          Long gameID = Long.parseLong(req.getParameter("gameId"));

          Game game= ofy().load().key(Key.create(Game.class,gameID)).get();
          game.setNumPlayers(game.getNumPlayers()+1);
          resp.sendRedirect("/joingame.jsp");
    }
    
}