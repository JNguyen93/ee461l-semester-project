package gamefinder;
//sh6HF2QQ9rW3
import java.util.Calendar;
import java.util.logging.Logger;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;



@Entity
public class Game {
	@Id
	private Long id;
	private String sport;
	private Calendar date;
	private String locationName;
	
	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
	public Game(){}
	
	public String getSport(){
		return sport;
	}
	
	public void setTime(int hour, int min, String AMPM){

		
	}
	
	public void setEndTime(int hour, int min, String AMPM){
		
	}
	
	public void setSport(String sport){
		this.sport = sport;
	}
	
	public Calendar getDate(){
		return date;
	}
	
	public String getLocation(){
		return locationName;
	}
	
	
}
