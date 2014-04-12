package gamefinder;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

import gamefinder.GameServlet;

import com.google.appengine.api.users.*;

public class GameServlet extends HttpServlet {

	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserService userService = UserServiceFactory.getUserService();

        User user = userService.getCurrentUser();

        String sportName = req.getParameter("sport");
        String beginTime = req.getParameter("beginTime");
        String beginAMorPM = req.getParameter("beginAMorPM");
        String endAMorPM = req.getParameter("endAMorPM");
        String endTime = req.getParameter("endTime");
      

       Game game = new Game();
       //game.setSport(sportName);
       //game.setLocation();
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
        
        _log.info("New game created!");
    }

}