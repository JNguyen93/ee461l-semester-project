package gamefinder;

import java.util.Calendar;
import java.util.Observable;
import java.util.TimeZone;
import java.util.logging.Logger;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
//import com.google.gwt.maps.client.impl.LatLngImpl;



@Entity
public class Game<LatLng> implements Comparable<Game>{
    @Id private Long id;
    private String sport;
    private Calendar date;
    private double[] location = new double[2];
    //location[0] = latitude
    //location[1] = longitude
    private String locationName;
    private String locationAddress;
    private int hour_s;
    private int min_s;
    private String ampm_s;
    private int hour_e;
    private int min_e;
    private String ampm_e;
    private int numPlayers;
    private int gameID;
    private int numOfPlayers;
	private Boolean emailAlerts;
	private Boolean smsAlerts;
	
	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
	public Game(){
		
	}
	
	public String getSport(){
		return sport;
	}
	public void setSport(String sport){
		this.sport = sport;
		//if(emailAlerts){
		//	setChanged();
		//	notifyObservers();
		//}
	}
	
	public void setID(int id){
		this.gameID = id;
	}
	public int getID(){
		return gameID;
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
	
	public void setNumPlayers(int numPlayers){
		this.numPlayers = numPlayers;
	}
	
	public int getNumPlayers(){
		return numPlayers;
	}
	
	public void joinGame(){
		this.numPlayers += 1;
	}
	
	public void setEndTime(int hour, int min, String AMPM){
		this.hour_e = hour;
		this.min_e = min;
		this.ampm_e = AMPM;
	//	date.set(Calendar.HOUR,hour);
	//	date.set(Calendar.MINUTE,min);
	//	date.set(Calendar.AM_PM,AMPM);
	}
	
    public double[] getLocation(){    
            return location;
    }
    
    public void setLocation(double lat, double lng){
    	location[0] = lat;
    	location[1] = lng;
    }
    
    public void setLocationName(String name){
    	locationName = name;
    }
    
    public String getLocationName(){
    	return locationName;
    }
    
    public void setLocationAddress(String address){
    	locationAddress = address;
    }

	public String getEndTime(){
		String endTime;
		endTime = hour_e + min_e + ampm_e;
		return endTime;
	}
	
	public Calendar getDate(){
		return date;
	}
	
	public int getNumOfPlayers(){
		return numOfPlayers;
	}
	
	public void setNumOfPlayers(int players){
		this.numOfPlayers=players;
	}
	
	public void setEmailAlerts(Boolean b){
		emailAlerts = b;
	}

	public void setSmsAlerts(Boolean b){
		smsAlerts = b;
	}
	
    public String getLocationAddress(){
    	return locationAddress;
    }

	@Override
	public int compareTo(Game o) {
		if (this.id == (o.getID())) {
            return 1;
        } else if (this.id != (o.getID())) {
            return -1;
        }
		return 0;
	}
 	
}
