package gamefinder;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;

public class SubscriptionServlet  extends HttpServlet {
	
	static {
        ObjectifyService.register(Email.class);
    }
	
	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {	 
		//combined subscribe and unsubscribe into one method.
        Email email = new Email(req.getUserPrincipal().getName());
        try{
        	Long gameID = Long.parseLong(req.getParameter("gameId"));
            Game game= ofy().load().key(Key.create(Game.class,gameID)).get();
            if(game.isSubscribed(email.getAddress())){
            	_log.info(email+" is subscribed already!");
            	_log.info(email+" is now being unsubscribed!");
            	game.removeEmail(email.getAddress());
            }
            else{
            	if(game.addEmail(email.getAddress()))
            		_log.info(email+" is now subscribed!");
            }
        	ofy().save().entity(game).now();
            resp.sendRedirect("/home.jsp?");
        }
        catch(IllegalArgumentException e){
            resp.sendRedirect("/home.jsp?");
        }
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// unsubscribe to game
		Email email = new Email(req.getUserPrincipal().getName());
        try{
        	Long gameID = Long.parseLong(req.getParameter("gameId"));
            Game game= ofy().load().key(Key.create(Game.class,gameID)).get();
            game.removeEmail(email.getAddress());
        	ofy().save().entity(game).now();
            resp.sendRedirect("/home.jsp?");
        }
        catch(IllegalArgumentException e){
            resp.sendRedirect("/home.jsp?");
        }
	}
}
