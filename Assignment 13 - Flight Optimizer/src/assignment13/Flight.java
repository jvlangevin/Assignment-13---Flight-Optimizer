package assignment13;

public class Flight {

	Airport origin;
	Airport destination;
	String carrier;
	int delay;
	boolean canceled;
	int time;
	int distance;
	double cost;
	
	public Flight(Airport origin, Airport destination, String carrier, int delay, boolean canceled, int time, int distance, double cost){
		
		this.origin = origin;
		this.destination = destination;
		this.carrier = carrier;
		this.delay = delay;
		this.canceled = canceled;
		this.time = time;
		this.distance = distance;
		this.cost = cost;
	}
}
