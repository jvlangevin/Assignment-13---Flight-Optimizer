package assignment13;

public class Flight {

	private Airport origin;
	private Airport destination;
	private String carrier;
	private int delay;
	private boolean canceled;
	private int time;
	private int distance;
	private double cost;
	
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
