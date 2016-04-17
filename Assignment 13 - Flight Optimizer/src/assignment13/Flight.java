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

	public Flight(Airport origin, Airport destination, String carrier, double delay, double canceled, int time, int distance,
			double cost){

		this.carrier = new ArrayList<String>();

		this.origin = origin;
		this.destination = destination;
		this.carrier.add(carrier);
		this.delay = delay;
		this.canceled = canceled;
		this.time = time;
		this.distance = distance;
		this.cost = cost;
		this.numFlights = 1;
	}

	public Flight(Airport origin, Airport destination, ArrayList<String> carriers, double newDelay, double canceled,
			double newTime, double newDistance, double cost, double newNumFlights){

		this.origin = origin;
		this.destination = destination;
		this.carrier = carriers;
		this.delay = newDelay;
		this.canceled = canceled;
		this.time = newTime;
		this.distance = newDistance;
		this.cost = cost;
		this.numFlights = newNumFlights;
	}

	public String toString() {

		return "Origin: " + this.origin.toString() + ",  Destination: " + this.destination.toString() + ",  Carrier: "
				+ this.carrier.toString() + ",  Delay: " + this.delay + ",  Canceled: " + this.canceled + ",  Time: " + this.time
				+ ",  Distance: " + this.distance + ",  Cost: " + this.cost + ",  Flights: " + numFlights;
	}

	public String destinationString() {

		return "" + this.origin.toString() + "," + this.destination.toString();
	}

	public Airport getOrigin() {
		return this.origin;
	}

	public Airport getDestination() {
		return this.destination;
	}

	public ArrayList<String> getCarrier() {
		return this.carrier;
	}

	public double getDelay() {

		return this.delay;
	}

	public double addDelay(double delayInput) {

		return this.delay += delayInput;
	}

	public double getTime() {
		return this.time;
	}

	public double addTime(double time) {
		return this.time += time;
	}

	public double getDistance() {
		return this.distance;
	}

	public double addDistance(double distance) {
		return this.distance += distance;
	}

	public double getCost() {
		return this.cost;
	}

	public double addCost(double d) {
		return this.cost += d;
	}

	public double getNumFlights() {
		return this.numFlights;
	}

	public double addNumFlights(double numFlights) {
		return this.numFlights += numFlights;
	}

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
