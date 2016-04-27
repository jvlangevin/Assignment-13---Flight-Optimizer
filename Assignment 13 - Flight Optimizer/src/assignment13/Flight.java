package assignment13;

import java.util.ArrayList;


/**
 * This class defines what is a flight, what it contains and what it does.
 * A flight is a path between two airports, an origin and a destination. 
 * Each flight has differing flight criteria: carriers, time delay, previous cancellations,
 * time of flight, distance of flight and a cost (money). 
 * 
 * @author Nathan and Jason
 *
 */
public class Flight {

	private Airport origin;
	private Airport destination;
	private ArrayList<String> carrier;
	private double delay;
	private double canceled;
	private double time;
	private double distance;
	private double cost;
	private double numFlights;

	
	/**
	 * Constructs a new Flight Path with details from one node to another (no connections)
	 * @param origin - the airport traveling from
	 * @param destination - the airport traveling to
	 * @param carrier - the company who owns the plane
	 * @param delay - the delay of departure
	 * @param canceled - where this flight had previously been cancelled
	 * @param time - the time the flight takes
	 * @param distance - the distance the flight travels.
	 * @param cost - the cost in $'s
	 */
	public Flight(Airport origin, Airport destination, String carrier, double delay, double canceled, int time, int distance,
			double cost){

		this.carrier = new ArrayList<String>();

		this.origin = origin;
		this.destination = destination;
		this.carrier.add(carrier); //for adding flights together we store the carriers in an arraylist<string>;
		this.delay = delay;
		this.canceled = canceled;
		this.time = time;
		this.distance = distance;
		this.cost = cost;
		this.numFlights = 1; // a new flight starts at one flight.
	}

	/**
	 * Returns each flightpath's details.
	 */
	public String toString() {

		return "Origin: " + this.origin.toString() + ",  Destination: " + this.destination.toString() + ",  Carrier: "
				+ this.carrier.toString() + ",  Delay: " + this.delay + ",  Canceled: " + this.canceled + ",  Time: " + this.time
				+ ",  Distance: " + this.distance + ",  Cost: " + this.cost + ",  Flights: " + numFlights + "\n";
	}

	/**
	 * 
	 * @return the origin airport's symbol +,+ the destination airport's symbols
	 * Used as a key for flight hashmap in NetworkGraph
	 */
	public String destinationString() {

		return "" + this.origin.toString() + "," + this.destination.toString();
	}

	/**
	 * Returns the carriers for a specific flight path
	 * @return
	 */
	public ArrayList<String> getCarrier()
	{
		return carrier;
	}

	/**
	 * Takes a flight from a parameter and adds it to this flight.
	 * @param inputFlight
	 * @throws Exception
	 */
	public void addFlights(Flight inputFlight) throws Exception {

		//you can not add flights if the origin and destinations between two flights don't match
		if (!(this.destinationString().equals(inputFlight.destinationString()))) {
			throw new Exception("You are adding two flights with incompatible origin and destinations");
		}

	
		else {

			//adds the carrier to our existing ArrayList
			for (String newCarrier : inputFlight.carrier) {
				if (!this.carrier.contains(newCarrier)) {
					this.carrier.add(newCarrier);
				}
			}

			//adds each flight value from input to existing, including # of flights which is used for averaging
			this.delay += inputFlight.delay;
			this.time += inputFlight.time;
			this.distance += inputFlight.distance;
			this.cost += inputFlight.cost;
			this.numFlights += inputFlight.numFlights;
			this.canceled += inputFlight.canceled;

		}

	}

	/**
	 * Takes the numerical values associated with the flight (delay, time distance, cost, canceled, numFlights)
	 *  and divides by the number of flights we've found with similar flight path's and updates the flight it;s
	 *  called it with the new values.
	 */
	public void averageFlight() {

		this.delay = this.delay / numFlights;
		this.time = this.time / numFlights;
		this.distance = this.distance / numFlights;
		this.cost = this.cost / numFlights;
		this.canceled = this.canceled / numFlights;
		this.numFlights = 1;
	}
	
	
	/**
	 * Returns the weight value of this flight based on the specified flight attribute.
	 * @param attr - a flight attribute that will serve as the weight value for this flight
	 */
	public double getWeight(String attr){
		
		switch(attr){
			
			case "COST":
				return this.cost;
			case "DELAY":
				return this.delay;
			case "DISTANCE":
				return this.distance;
			case "CANCELED":
				return this.canceled;
			case "TIME":
				return this.time;
		}
		return 0;
	}


}
