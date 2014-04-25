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
            Ref<Game> game = ObjectifyService.ofy().load().type(Game.class)
            		.id(req.getParameter("id"));
            ArrayList<Email> list = game.get().getEmailList();
        	if(!list.contains(email)){
        		list.add(email);
        		ofy().save().entity(game).now();
        	}
            resp.sendRedirect("/joingame.jsp?");
        }
        catch(IllegalArgumentException e){
            resp.sendRedirect("/joingame.jsp?");
        }
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try{
			ObjectifyService.register(Email.class);
			List<Email> emails = ObjectifyService.ofy().load().type(Email.class).list();   
			Collections.sort(emails);
			Email email = new Email(req.getUserPrincipal().getName());
			for(Email email1 : emails){
				if(email1.getAddress().equals(email.getAddress())){
					ofy().delete().entity(email1).now();
				}
			}
            resp.sendRedirect("/joingame.jsp?");
		}
		catch(IllegalStateException e){
	        resp.sendRedirect("/joingame.jsp?");
		}
	}
}
