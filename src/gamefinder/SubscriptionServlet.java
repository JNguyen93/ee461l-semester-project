package gamefinder;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {	        
        Email email = new Email(req.getUserPrincipal().getName());
        try{
        	Long gameID = Long.parseLong(req.getParameter("gameId"));
            Game game= ofy().load().key(Key.create(Game.class,gameID)).get();
            game.addEmail(email.getAddress());
        	ofy().save().entity(game).now();
            resp.sendRedirect("/joingame.jsp?");
        }
        catch(IllegalArgumentException e){
            resp.sendRedirect("/joingame.jsp?");
        }
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Email email = new Email(req.getUserPrincipal().getName());
        try{
        	Long gameID = Long.parseLong(req.getParameter("gameId"));
            Game game= ofy().load().key(Key.create(Game.class,gameID)).get();
            game.removeEmail(email.getAddress());
        	ofy().save().entity(game).now();
            resp.sendRedirect("/joingame.jsp?");
        }
        catch(IllegalArgumentException e){
            resp.sendRedirect("/joingame.jsp?");
        }
	}
}
