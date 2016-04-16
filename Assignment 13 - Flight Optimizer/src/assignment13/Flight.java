package assignment13;

import java.util.ArrayList;

public class Flight {

	private Airport origin;
	private Airport destination;
	private ArrayList<String> carrier;
	private double delay;
	private boolean canceled;
	private double time;
	private double distance;
	private double cost;
	private double numFlights;
	

	
	public Flight(Airport origin, Airport destination, String carrier, int delay, boolean canceled, int time, int distance, double cost){
		
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
	
	public Flight(Airport origin, Airport destination, ArrayList<String> carriers, double newDelay, boolean canceled,
			double newTime, double newDistance, double cost, double newNumFlights) {
		
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

	public String toString(){
		
		return ""+this.origin.toString()+","+this.destination.toString()+","+this.carrier.toString()+","+this.delay+","+this.canceled+","+this.time+","+this.distance+","+this.cost+","+numFlights;
	}
	
	public String destinationString(){
		
		return ""+this.origin.toString()+","+this.destination.toString();
	}
	
	public Airport getOrigin()
	{
		return this.origin;
	}
	
	public Airport getDestination()
	{
		return this.destination;
	}
	
	public ArrayList<String> getCarrier()
	{
		return this.carrier;
	}
	
	public double getDelay(){
		
		return this.delay;
	}
	
	public double addDelay(double delayInput){
		
		return this.delay += delayInput;
	}
	
	public boolean isCanceled()
	{
		return this.canceled;
	}

	public double getTime()
	{
		return this.time;
	}
	
	public double addTime(double time)
	{
		return this.time += time;
	}
	
	public double getDistance()
	{
		return this.distance;
	}
	
	public double addDistance(double distance)
	{
		return this.distance += distance;
	}
	
	public double getCost()
	{
		return this.cost;
	}
	
	public double addCost(double d)
	{
		return this.cost += d;
	}
	
	public double getNumFlights()
	{
		return this.numFlights;
	}
	
	public double addNumFlights(double numFlights)
	{
		return this.numFlights += numFlights;
	}
	
	
	public Flight addFlights(Flight inputFlight) throws Exception
	{
		
		if(!(this.destinationString().equals(inputFlight.destinationString())))
		{
			throw new Exception("You are adding two flights with incompatible origin and destinations");
		}
		
		/* for if we need to filter out cancelled flights
		else if(this.isCanceled() || inputFlight.isCanceled())
		{
			throw new Exception("One of your flights is a cancelled flight.");
		}
		*/
		else{
		
			
			ArrayList<String> newCarriers = new ArrayList<>();
			
			for(String carrier : this.getCarrier())
			{	
				if(!newCarriers.contains(carrier))
				{
					newCarriers.add(carrier);
				}
			}
			for(String carrier : inputFlight.getCarrier())
			{
				if(!newCarriers.contains(carrier))
				{
					newCarriers.add(carrier);
				}
			}
			
			double newDelay = this.addDelay(inputFlight.getDelay());
			double newTime = this.addTime(inputFlight.getTime());
			double newDistance = this.addDistance(inputFlight.getDistance());
			double newCost = this.addCost(inputFlight.getCost());
			double newNumFlights = this.addNumFlights(inputFlight.getNumFlights());
			
			Flight newFlight = new Flight(this.origin, this.destination, newCarriers, newDelay, false, newTime, newDistance, newCost, newNumFlights);
			
			return newFlight;
		}
		
	}
	
	public void averageFlight(){

		this.delay = this.getDelay()/numFlights;
		this.time = this.getTime()/numFlights;
		this.distance = this.getDistance()/numFlights;
		this.cost = this.getCost()/numFlights;
		this.numFlights = 1;
		
	}
	
	
}
