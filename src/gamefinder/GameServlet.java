package gamefinder;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

import gamefinder.GameServlet;

import com.google.appengine.api.users.*;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GameServlet extends HttpServlet {
	static {
        ObjectifyService.register(Game.class);
    }

	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        String sportName = req.getParameter("sport");
        String beginTime = req.getParameter("beginTime");
        String beginAMorPM = req.getParameter("beginAMorPM");
        String endAMorPM = req.getParameter("endAMorPM");
        String endTime = req.getParameter("endTime");


        //String test = req.getParameter("endTimeMin");
        //_log.info(sportName);
        
        int beginTimeHour = Integer.parseInt(req.getParameter("beginTimeHour"));
        int beginTimeMin = Integer.parseInt(req.getParameter("beginTimeMin"));
        String beginAMPM = req.getParameter("beginAMPM");
        String endAMPM = req.getParameter("endAMPM");
        int endTimeHour = Integer.parseInt(req.getParameter("endTimeHour"));
        int endTimeMin = Integer.parseInt(req.getParameter("endTimeMin"));

       Game game = new Game();

       game.setSport(sportName);
       //game.setSport(sportName);

     //  game.setSport(sportName);
       game.setStartTime(beginTimeHour, beginTimeMin, beginAMPM);
       game.setEndTime(endTimeHour, endTimeMin, endAMPM);

       
       // saving Location
       String longitude1 = req.getParameter("longitude");
       String latitude1 = req.getParameter("latitude");
       _log.info(longitude1 +" "+ latitude1);
       
       double latitude = Double.parseDouble(req.getParameter("latitude"));
       double longitude = Double.parseDouble(req.getParameter("longitude"));
       game.setLocation(latitude, longitude);


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
        

       
       ofy().save().entity(game).now();
       //resp.sendRedirect("/");
       _log.info("Game from " + beginTimeHour +":" + beginTimeMin + " until " + endTimeHour + ":" + endTimeMin);

        _log.info("New game created!");
    }

}