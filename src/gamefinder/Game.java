package gamefinder;

import java.util.Observable;
import java.util.Date;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

//import com.google.gwt.maps.client.impl.LatLngImpl;



@Entity
public class Game<LatLng> implements Comparable<Game>{
    @Id private Long id;
    private String sport;

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
    private int gameIndex;
    private int maxPlayers;
    private Date date=new Date();
    private int year=date.getYear();
    private int month=date.getMonth();
    private int day=date.getDay();
   
        
 //   public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
    
    public Game(){
    }
    public void updateDate(){
    	this.date.setYear(year+1900);
    	this.date.setMonth(month);
    	this.date.setDate(day);
    }
    public int getYear(){
    	return year;
    }
    public void setYear(int Year){
    	this.year=Year;
    	updateDate();
    }
    public int getMonth(){
            return month;
    }
    public void setMonth(int Month){
    	this.month=Month;
    	updateDate();
    }
    public int getDay(){
    	return day;
    }
    public void setDay(int Day){
    	this.day=Day;
    	updateDate();
    }
    public String getSport(){
            return sport;
    }
    public Long getID(){
    	return id;
    }
    
    public void setIndex(int index){
            this.gameIndex = index;
    }
    public int getIndex(){
            return gameIndex;
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
    public void setMaxPlayers(int max){
    	this.maxPlayers=max;
    }
    public int getMaxPlayers(){
    	return maxPlayers;
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
    //      date.set(Calendar.HOUR,hour);
    //      date.set(Calendar.MINUTE,min);
    //      date.set(Calendar.AM_PM,AMPM);
    }
        
    public String getEndTime(){
            String endTime;
            endTime = hour_e + min_e + ampm_e;
            return endTime;
    }
    
    
  

	private Boolean emailAlerts;
//	private Boolean smsAlerts;
	
//	public static final Logger _log = Logger.getLogger(GameServlet.class.getName());
	
	
	
	public void setSport(String sport){
		this.sport = sport;
		//if(emailAlerts){
		//	setChanged();
		//	notifyObservers();
		//}
	}



    public double[] getLocation(){    
            return location;
    }
    
    public double getLat(){
    	return location[0];
    }
    
    public double getLng(){
    	return location[1];
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


//	public Calendar getDate(){
//		return date;
//	}
//	
	
	
	public void setEmailAlerts(Boolean b){
		emailAlerts = b;
	}

//	public void setSmsAlerts(Boolean b){
//		smsAlerts = b;
//	}
	
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
