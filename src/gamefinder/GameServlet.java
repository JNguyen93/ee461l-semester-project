package gamefinder;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

import gamefinder.GameServlet;

import com.google.appengine.api.users.*;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class GameServlet extends HttpServlet {

	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        String sportName = req.getParameter("sport");
        int beginTimeHour = Integer.parseInt(req.getParameter("beginTimeHour"));
        int beginTimeMin = Integer.parseInt(req.getParameter("beginTimeMin"));
        String beginAMPM = req.getParameter("beginAMPM");
        String endAMPM = req.getParameter("endAMPM");
        int endTimeHour = Integer.parseInt(req.getParameter("endTimeHour"));
        int endTimeMin = Integer.parseInt(req.getParameter("endTimeMin"));
        
        //HOW TO GET LOCATION WTF
        
       Game game = new Game();
       game.setSport(sportName);
       game.setTime(beginTimeHour, beginTimeMin, beginAMPM);
       game.setEndTime(beginTimeHour, beginTimeMin, beginAMPM);
       //game.setLocation();
       
       ofy().save().entity(game).now();
       resp.sendRedirect("/");
        
        _log.info("New game created!");
    }

}