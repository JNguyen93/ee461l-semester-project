package gamefinder;
//sh6HF2QQ9rW3
import java.util.Calendar;

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
	
	public Game(){
		
	}
	
	public String getSport(){
		return sport;
	}
	
	public Calendar getDate(){
		return date;
	}
	
	public String getLocation(){
		return locationName;
	}
	
	
}
