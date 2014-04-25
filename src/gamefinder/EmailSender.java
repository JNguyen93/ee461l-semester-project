package gamefinder;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.googlecode.objectify.Key;

public class EmailSender {
	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
	public static void sendEmail(Game game){
		try{
           	ArrayList<String> list = game.getEmailList();
        	String message = "Hi";
    		Properties props = new Properties();
    		Session session = Session.getDefaultInstance(props, null);
    		MimeMessage outMessage = new MimeMessage(session);
    		outMessage.setFrom(new InternetAddress("admin@ee461l-gamefinder.appspotmail.com"));
    		outMessage.setSubject("A game you have signed up for has changed!");
    		outMessage.setText(message);
           	for(String email: list){
           		outMessage.addRecipient(MimeMessage.RecipientType.BCC,
    					new InternetAddress(email));
           		Transport.send(outMessage);
           	}
    		// DEBUGGING - DISPLAYING EMAIL IN LOG
    				_log.info(message);
    		}
    		catch(MessagingException e){
    			_log.info("ERROR: Could not send out Email Results response : " 
    														+ e.getMessage());
    		}
	}
	
}
