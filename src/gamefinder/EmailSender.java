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




import org.json.JSONObject;

import com.googlecode.objectify.Key;

public class EmailSender {
	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
	public static void sendEmail(Game game){
		try{
           	ArrayList<String> list = game.getEmailList();
           	/*String locationAddress = reverseGeocoding(game.getLat(), game.getLng());
        	String message = "There are now enough players for " + game.getSport()
        			+ " at " + game.getStartTime() + ". See you at the game! The game will be held at " +
        			game.getLocationName() + " located at " + locationAddress +".\n\n Here is a link to the location!\n" +
        			"http://maps.google.com/?q="+game.getLat()+","+game.getLng();
        			*/
           	String message = "There are now enough players for " + game.getSport()
        			+ " at " + game.getStartTime() + ". See you at the game! The game will be held at " +
        			game.getLocationName() + ".\n\n Here is a link to the location!\n" +
        			"http://maps.google.com/?q="+game.getLat()+","+game.getLng();
    		Properties props = new Properties();
    		Session session = Session.getDefaultInstance(props, null);
    		MimeMessage outMessage = new MimeMessage(session);
    		outMessage.setFrom(new InternetAddress("admin@ee461l-gamefinder.appspotmail.com"));
    		outMessage.setSubject("A new game awaits!");
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
		
		public static void sendSingleEmail(Game game, String email){
			try{
				/*String locationAddress = reverseGeocoding(game.getLat(), game.getLng());
	        	String message = "There are now enough players for " + game.getSport()
	        			+ " at " + game.getStartTime() + ". See you at the game! The game will be held at " +
	        			game.getLocationName() + " located at " + locationAddress +".\n\n Here is a link to the location!\n" +
	        			"http://maps.google.com/?q="+game.getLat()+","+game.getLng();
	        			*/
	           	String message = "There are now enough players for " + game.getSport()
	        			+ " at " + game.getStartTime() + ". See you at the game! The game will be held at " +
	        			game.getLocationName() + ".\n\n Here is a link to the location!\n" +
	        			"http://maps.google.com/?q="+game.getLat()+","+game.getLng();
	    		Properties props = new Properties();
	    		Session session = Session.getDefaultInstance(props, null);
	    		MimeMessage outMessage = new MimeMessage(session);
	    		outMessage.setFrom(new InternetAddress("admin@ee461l-gamefinder.appspotmail.com"));
	    		outMessage.setSubject("A new game awaits!");
	    		outMessage.setText(message);
	           	outMessage.addRecipient(MimeMessage.RecipientType.BCC,
	    				new InternetAddress(email));
	           	Transport.send(outMessage);
	    		// DEBUGGING - DISPLAYING EMAIL IN LOG
	    				_log.info(message);
	    		}
	    		catch(MessagingException e){
	    			_log.info("ERROR: Could not send out Email Results response : " 
	    														+ e.getMessage());
	    		}
	}
	//TODO : figure out how to reverse geocode and get string address
	private static String reverseGeocoding(double lat, double lng){
		String address = "";
		return address;
	}
	
}
