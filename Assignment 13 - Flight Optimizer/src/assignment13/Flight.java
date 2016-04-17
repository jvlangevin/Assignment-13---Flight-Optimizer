package assignment13;

import java.util.ArrayList;

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
	 * Constructor Used for processing flight addition. Carriers is passed as an array list of strings for
	 * each carrier from previous additions
	 */
	public Flight(Airport origin, Airport destination, ArrayList<String> carriers, double newDelay, double canceled,
			double newTime, double newDistance, double cost, double newNumFlights){

		this.origin = origin;
		this.destination = destination;
		this.carrier = carriers; //when adding a flight, all carriers from previous additions are passed as an arrayList
		this.delay = newDelay;
		this.canceled = canceled;
		this.time = newTime;
		this.distance = newDistance;
		this.cost = cost;
		this.numFlights = newNumFlights;
	}

	
	/**
	 * Returns each flightpath's details.
	 */
	public String toString() {

		return "Origin: " + this.origin.toString() + ",  Destination: " + this.destination.toString() + ",  Carrier: "
				+ this.carrier.toString() + ",  Delay: " + this.delay + ",  Canceled: " + this.canceled + ",  Time: " + this.time
				+ ",  Distance: " + this.distance + ",  Cost: " + this.cost + ",  Flights: " + numFlights;
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
	 * Returns this flight's origin
	 */
	public Airport getOrigin() {
		return this.origin;
	}

	/**
	 * Returns this flights destination
	 */
	public Airport getDestination() {
		return this.destination;
	}

	/**
	 * Returns this flights carrier or carriers from previous additions.
	 * @return
	 */
	public ArrayList<String> getCarrier() {
		return this.carrier;
	}

	/**
	 * gets the delay for this flight
	 * @return
	 */
	public double getDelay() {

		return this.delay;
	}

	/**
	 * Used to add on to this flights delay
	 * @param delayInput - the value to increase the delay by
	 * @return
	 */
	public double addDelay(double delayInput) {

		return this.delay += delayInput;
	}

	/**
	 * @return the travel time of this flight
	 */
	public double getTime() {
		return this.time;
	}

	/**
	 * @param time the value of which to increase the travel time by
	 * @return the new total travel time
	 */
	public double addTime(double time) {
		return this.time += time;
	}

	
	/**
	 * @return the distance the flight took
	 */
	public double getDistance() {
		return this.distance;
	}

	/**
	 * @param distance - the value which will be added to existing travel distance
	 * @return the new distance after adding
	 */
	public double addDistance(double distance) {
		return this.distance += distance;
	}

	/**
	 * gets the cost of this flight
	 * @return the cost
	 */
	public double getCost() {
		return this.cost;
	}

	/**
	 * @param cost -the value to add on to our existing cost
	 * @return the new cost
	 */
	public double addCost(double cost) {
		return this.cost += cost;
	}

	/**
	 * @return the number of existing flights with exact origin and destinations
	 */
	public double getNumFlights() {
		return this.numFlights;
	}

	/**
	 * @param numFlights the value to add to existing number of flights
	 * @return the new number of flights
	 */
	public double addNumFlights(double numFlights) {
		return this.numFlights += numFlights;
	}

	
	/**
	 * This method takes the flight it's called on and an input flight and adds the values
	 * together if the flight path's match. 
	 * @param inputFlight - the second flight we are adding to this flight
	 * @return a new flight of the combined totals
	 * @throws Exception - if someone add's two flights with incorrect path's
	 */
	public Flight addFlights(Flight inputFlight) throws Exception {

		if (!(this.destinationString().equals(inputFlight.destinationString()))) {
			throw new Exception("You are adding two flights with incompatible origin and destinations");
		}

		/*
		 * for if we need to filter out cancelled flights else if(this.isCanceled() || inputFlight.isCanceled()) { throw
		 * new Exception("One of your flights is a cancelled flight."); }
		 */
		else {

			ArrayList<String> newCarriers = new ArrayList<>();

			for (String carrier : this.getCarrier()) {
				if (!newCarriers.contains(carrier)) {
					newCarriers.add(carrier);
				}
			}
			for (String carrier : inputFlight.getCarrier()) {
				if (!newCarriers.contains(carrier)) {
					newCarriers.add(carrier);
				}
			}

			double newDelay = this.addDelay(inputFlight.getDelay());
			double newTime = this.addTime(inputFlight.getTime());
			double newDistance = this.addDistance(inputFlight.getDistance());
			double newCost = this.addCost(inputFlight.getCost());
			double newNumFlights = this.addNumFlights(inputFlight.getNumFlights());
			double newCanceled = this.canceled + inputFlight.canceled;

			Flight newFlight = new Flight(this.origin, this.destination, newCarriers, newDelay, newCanceled, newTime, newDistance,
					newCost, newNumFlights);

			return newFlight;
		}

	}

	/**
	 * Takes the numerical values associated with the flight (delay, time distance, cost, canceled, numFlights)
	 *  and divides by the number of flights we've found with similar flight path's and updates the flight it;s
	 *  called it with the new values.
	 */
	public void averageFlight() {

		this.delay = this.getDelay() / numFlights;
		this.time = this.getTime() / numFlights;
		this.distance = this.getDistance() / numFlights;
		this.cost = this.getCost() / numFlights;
		this.canceled = canceled / numFlights;
		this.numFlights = 1;
	}

	public static void main(String[] args) throws Exception {

		Flight f1 = new Flight(new Airport("ZEF"), new Airport("SNF"), "UA", 31, 1, 236, 1631, 537.41);
		Flight f2 = new Flight(new Airport("ZEF"), new Airport("SNF"), "OO", 219, 0, 236, 1631, 217.42);
		Flight f3 = f1.addFlights(f2);
		Flight f4 = new Flight(new Airport("ZEF"), new Airport("SNF"), "DL", 219, 0, 236, 1631, 217.42);
		Flight f5 = f3.addFlights(f4);

		f5.averageFlight();
		System.out.println(f5.canceled);

	}

}
