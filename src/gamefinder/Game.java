package gamefinder;
//sh6HF2QQ9rW3
import java.util.Calendar;
import java.util.logging.Logger;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.google.gwt.maps.client.impl.LatLngImpl;



@Entity
public class Game<LatLng> {
	@Id
	private Long id;
	private String sport;
	private Calendar date;
	private LatLng location;
	
	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
	public Game(){}
	
	public String getSport(){
		return sport;
	}
	public void setSport(String sport){
		this.sport = sport;
	}
	public void setStartTime(int hour, int min, int AMPM){
		date.set(Calendar.HOUR,hour);
		date.set(Calendar.MINUTE,min);
		date.set(Calendar.AM_PM,AMPM);
	}
	public Calendar getStartTime(){
		return date;
	}
	
	public void setEndTime(int hour, int min, int AMPM){
		date.set(Calendar.HOUR,hour);
		date.set(Calendar.MINUTE,min);
		date.set(Calendar.AM_PM,AMPM);
	}
	public Calendar getEndTime(){
		return date;
	}
	
	
	public Calendar getDate(){
		return date;
	}
	public void setLocation(LatLng newLoc){
		this.location=newLoc;
	}

	public LatLng getLocation(){
		
		return location;
	}
	
	
}
