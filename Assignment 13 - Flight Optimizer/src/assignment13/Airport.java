package assignment13;

import java.util.ArrayList;

public class Airport implements Comparable{
	
	
	//Member variables
	private String code;
	private boolean visited;
	private ArrayList<Airport> connections;
	private Airport previous;
	private double cost;
	
	/**
	 * Constructor. Constructs an airport will will act as a node for graphing purposes.
	 * @param code
	 * 			- three character value that represents the airport
	 */
	public Airport(String code){
		this.code = code; 						//name of the airport
		this.visited = false; 					//whether it's been visisted when finding a path
		this.connections = new ArrayList<>(); 	// a list of destinations from this airport
		this.previous = null; 					// when finding a path, this is the airport that was before this one
		this.cost = Double.MAX_VALUE; 			//the accrued weight in the path. initialized as a pseudo-infinity
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
	 * Returns the code ("name") of the airport
	 */
	public String toString()
	{
		return this.code;
	}
	
	/**
	 * @return the string version of ArrayList<Airport> connections. Used for data testing.
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
	 * Marks this airport as not visited. Used when resetting a path
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

	/**
	 * Comparator used to determine which cost (accumulated weight) is less between airports.
	 * This determines the position in a priority queue.
	 */
	@Override
	public int compareTo(Object o) {
		//to allow override, param is an object
		
		//cast o, an object, as an airport.
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
