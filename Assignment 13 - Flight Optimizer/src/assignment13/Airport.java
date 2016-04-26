package assignment13;

import java.util.ArrayList;

public class Airport implements Comparable{
	
	private String code;
	private boolean visited;
	private ArrayList<Airport> connections;
	private Airport previous;
	private double cost;
	
	public Airport(String code){
		this.code = code;
		this.visited = false;
		this.connections = new ArrayList<>();
		this.previous = null;
		this.cost = Double.MAX_VALUE;
	}
	
	/**
	 * Returns the connections list for this airport.
	 */
	public ArrayList<Airport> connections(){
		return this.connections;
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
	
	/**
	 * Returns the airport that was visited immediately prior to this airport.
	 */
	public Airport previous(){
		return this.previous;
	}
	
	/**
	 * Designates the specified airport as the one that was visited prior to this airport.
	 * @param airport - the most recent airport visited prior to this airport
	 */
	public void setPrevious(Airport airport){
		
		this.previous = airport;
	}
	
	/**
	 * @return - true if this airport has been visited, false otherwise
	 */
	public boolean visited(){
		return this.visited;
	}
	
	/**
	 * Marks this airport as visited.
	 */
	public void setVisited(){
		this.visited = true;
	}
	
	/**
	 * Marks this airport as not visited.
	 */
	public void setNotVisited(){
		this.visited = false;
	}
	
	/**
	 * Returns the current cost value of this airport.
	 */
	public double cost(){
		return this.cost;
	}
	
	/**
	 * Sets the cost value of this airport to the specified value.
	 * @param newCost
	 */
	public void setCost(double newCost){
		this.cost = newCost;
	}

	@Override
	public int compareTo(Object o) {
		//to allow override, param is an object
		
		//cast o, an object, as an aiport.
		Airport destination = (Airport)o;
		if(this.cost() < destination.cost()){
			return -1;
		}
		else
		{
			return 1;
		}
	}

}
