package gamefinder;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Logger;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
//import com.google.gwt.maps.client.impl.LatLngImpl;



@Entity
public class Game<LatLng> {
	@Id
	private Long id;
	private String sport;
	private Calendar date;
	private LatLng location;
	private int hour_s;
	private int min_s;
	private String ampm_s;
	private int hour_e;
	private int min_e;
	private String ampm_e;
	
	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
	public Game(){}
	
	public String getSport(){
		return sport;
	}
	public void setSport(String sport){
		this.sport = sport;
	}
	public void setStartTime(int hour, int min, String AMPM){
		this.hour_s = hour;
		this.min_s = min;
		this.ampm_s = AMPM;
		//date.set(Calendar.HOUR,hour);
		//date.set(Calendar.MINUTE,min);
		//date.set(Calendar.AM_PM,AMPM);
	}
	public String getStartTime(){
		String startTime;
		startTime = hour_s + min_s + ampm_s;
		return startTime;
	}
	
	public void setEndTime(int hour, int min, String AMPM){
		this.hour_e = hour;
		this.min_e = min;
		this.ampm_e = AMPM;
	//	date.set(Calendar.HOUR,hour);
	//	date.set(Calendar.MINUTE,min);
	//	date.set(Calendar.AM_PM,AMPM);
	}
	

	
	public String getEndTime(){
		String endTime;
		endTime = hour_e + min_e + ampm_e;
		return endTime;
	}
	
	
	public Calendar getDate(){
		return date;
	}

	/*
	public String getLocation(){
		return locationName;
=======
	public void setLocation(LatLng newLoc){
		this.location=newLoc;
>>>>>>> .r32
	}*/
	
	
/*	public void setLocation(String locationName){
		this.locationName= locationName;
	}
<<<<<<< .mine
	*/


	public LatLng getLocation(){
		
		return location;
	}

	
}
