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
			a.connections.add(this);
		}
	}
	
	public String toString()
	{
		return this.code;
	}
	
}
