package gamefinder;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Email implements Comparable<Email>{
    @Id Long id;
	String address;
	Long gameID;
		
	public Email(){}

	public Email(String address){
		this.address = address;
		this.id = (long) address.hashCode();
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setGameID(Long id){
		this.gameID = id;
	}
	
	public Long getGameID(){
		return gameID;
	}
	
	@Override
    public int compareTo(Email other) {
        if (address.equals(other.address)) {
            return 1;
        } else {
            return -1;
        }
    }

}
