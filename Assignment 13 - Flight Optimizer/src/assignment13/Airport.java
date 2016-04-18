package assignment13;

import java.util.ArrayList;

public class Airport {
	
	private String code;
	private boolean visited;
	private ArrayList<Airport> connections;
	private Airport previous;
	
	public Airport(String code){
		this.code = code;
		this.visited = false;
		this.connections = new ArrayList<>();
		this.previous = null;
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
	
	public void setPrevious(Airport airport){
		
		this.previous = airport;
	}
	
}
