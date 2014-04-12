package gamefinder;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

public class SubscriptionServlet  extends HttpServlet {
	
	static {
        ObjectifyService.register(Email.class);
    }
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {	        
        Email email = new Email(req.getParameter("address"));
        try{
        	if(!(ofy().equals(email))){
        		ofy().save().entity(email).now();
                resp.sendRedirect("/home.jsp?");
        	}
        }
        catch(IllegalArgumentException e){
            resp.sendRedirect("/home.jsp?");
        }
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try{
			ObjectifyService.register(Email.class);
			List<Email> emails = ObjectifyService.ofy().load().type(Email.class).list();   
			Collections.sort(emails);
			Email email = new Email(req.getParameter("address"));
			for(Email email1 : emails){
				if(email1.getAddress().equals(email.getAddress())){
					ofy().delete().entity(email1).now();
				}
			}
            resp.sendRedirect("/home.jsp?");
		}
		catch(IllegalStateException e){
	        resp.sendRedirect("/home.jsp?");
		}
	}
}
