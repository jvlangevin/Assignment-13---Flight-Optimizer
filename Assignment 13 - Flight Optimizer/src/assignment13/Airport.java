package assignment13;

import java.util.ArrayList;

public class Airport {
	
	private String code;
	private boolean visited;
	private ArrayList<Airport> connections;
	
	public Airport(String code){
		this.code = code;
		this.visited = false;
		this.connections = new ArrayList<>();
	}
	
	/**
	 * @param a - an airport
	 * @return - true if this airport connects to the airport specified in the parameter
	 */
	public boolean hasConnection(Airport a){
		return connections.contains(a);
	}
	
	/**
	 * Adds the specified airport to the connections list for this airport.
	 * @param a - an airport
	 */
	public void addConnection(Airport a){
		if(!this.hasConnection(a)){
			this.connections.add(a);
			
			//believe this to be incorrect;
			//pretend one way flights go in a triangle from salt lake to portland to L.A. then back to SLC
			//if you were traveling from Portland to SLC, you'd have to have a connection
			//at L.A. to get to SLC, but if you were traveling from LA to SLC, you wouldn't say
			//Portland is a connection just because LA was a connection from before. 
			//Connection is only if a flight travels to that location from the origin
			
			//a.connections.add(this);
		}
	}
	
	/**
	 * Returns the code of the airport
	 */
	public String toString()
	{
		return this.code;
	}
	
	/**
	 * @return the string version of ArrayList<Airport> connections.
	 */
	public String listDestinations()
	{
		return connections.toString();
	}
	
	/**
	 * @return the actual arrayList<Airport> for connections.
	 */
	public ArrayList<Airport> getDestinations(){
		
		return connections;
	}
	
}
