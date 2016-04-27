/**
 * 
 */
package assignment13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import sun.net.www.content.audio.aiff;

/**
 * <p>
 * This class represents a graph of flights and airports along with specific data about those flights. It is recommended
 * to create an airport class and a flight class to represent nodes and edges respectively. There are other ways to
 * accomplish this and you are free to explore those.
 * </p>
 * 
 * <p>
 * Testing will be done with different criteria for the "best" path so be sure to keep all information from the given
 * file. Also, before implementing this class (or any other) draw a diagram of how each class will work in relation to
 * the others. Creating such a diagram will help avoid headache and confusion later.
 * </p>
 * 
 * <p>
 * Be aware also that you are free to add any member variables or methods needed to completed the task at hand
 * </p>
 * 
 * @author CS2420 Teaching Staff - Spring 2016
 */
public class NetworkGraph {

	private Map<String, Flight> flightHash;
	private Map<String, Airport> airports;

	/**
	 * <p>
	 * Constructs a NetworkGraph object and populates it with the information contained in the given file. See the
	 * sample files or a randomly generated one for the proper file format.
	 * </p>
	 * 
	 * <p>
	 * You will notice that in the given files there are duplicate flights with some differing information. That
	 * information should be averaged and stored properly. For example some times flights are canceled and that is
	 * represented with a 1 if it is and a 0 if it is not. When several of the same flights are reported totals should
	 * be added up and then reported as an average or a probability (value between 0-1 inclusive).
	 * </p>
	 * 
	 * @param flightInfoPath
	 *            - The path to the file containing flight data. This should be a *.csv(comma separated value) file
	 * 
	 * @throws FileNotFoundException
	 *             The only exception that can be thrown in this assignment and only if a file is not found.
	 */
	public NetworkGraph(String flightInfoPath) throws FileNotFoundException{
		// appropriately in this object.
		BufferedReader reader = new BufferedReader(new FileReader(flightInfoPath));

		flightHash = new HashMap<String, Flight>(); // keep track of our flights (edges) key = origin name + , +
													// destination name
		airports = new HashMap<String, Airport>(); // keep track of our airports (nodes); key = origin name

		try {
			reader.readLine();

			while (reader.ready()) {

				String currentLine = reader.readLine();
				String[] flightInfo = currentLine.split(",");

				Airport origin;

				// if the origin airport already exists, sets this origin to the existing airport to maintain list of
				// connections
				if (airports.containsKey(flightInfo[0])) {
					origin = airports.get(flightInfo[0]);
				}

				// if origin airport doesn't already exist, creates it as a new airport
				else {
					origin = new Airport(flightInfo[0]);
					airports.put(flightInfo[0], origin);
				}

				// destination is also going to be an airport
				Airport destination;

				// so, if it already exists, don't create a new one, just reference the already
				// existing one.
				if (airports.containsKey(flightInfo[1])) {
					destination = airports.get(flightInfo[1]);
				}

				// if it doesn't already exist, then create a new one and add it to our airport hash
				else {
					destination = new Airport(flightInfo[1]);
					airports.put(flightInfo[1], destination);
				}

				// add that destination as a connection to our origin
				// since the connection is a list of references to other airports
				// adding a connection to our destination later will maintain a network
				// back to this origin as we progress, rather than create multiple airports
				// with the same name but different destinations
				origin.addConnection(destination);

				String carrier = flightInfo[2];
				int delay = Integer.parseInt(flightInfo[3]);
				int canceled = Integer.parseInt(flightInfo[4]);
				int time = Integer.parseInt(flightInfo[5]);
				int distance = Integer.parseInt(flightInfo[6]);
				double cost = Double.parseDouble(flightInfo[7]);

				Flight newFlight = new Flight(origin, destination, carrier, delay, canceled, time, distance, cost);

				// use this to add values from one flight to another
				try {
					flightHashAdd(newFlight);
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
			// Average out values for all flights having multiple carriers
			for (Flight f : flightHash.values()) {
				f.averageFlight();
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns a BestPath object containing information about the best way to fly to the destination from
	 * the origin. "Best" is defined by the FlightCriteria parameter <code>enum</code>. This method should throw no
	 * exceptions and simply return a BestPath object with information dictating the result. For example, if a
	 * destination or origin is not contained in this instance of NetworkGraph simply return a BestPath with no path
	 * (not a <code>null</code> path). If origin or destination are <code>null</code> return a BestPath object with null
	 * as origin or destination (which ever is <code>null</code>.
	 * 
	 * @param origin
	 *            - The starting location to find a path from. This should be a 3 character long string denoting an
	 *            airport.
	 * 
	 * @param destination
	 *            - The destination location from the starting airport. Again, this should be a 3 character long string
	 *            denoting an airport.
	 * 
	 * @param criteria
	 *            - This enum dictates the definition of "best". Based on this value a path should be generated and
	 *            return.
	 * 
	 * @return - An object containing path information including origin, destination, and everything in between.
	 */
	public BestPath getBestPath(String origin, String destination, FlightCriteria criteria) {

		ArrayList<Airport> adjustedAirports = new ArrayList<Airport>();
		
		if(origin == null || destination == null){
			ArrayList<String> al = new ArrayList<>();
			al.add(origin);
			al.add(destination);
			return new BestPath(al, 0);
		}
		
		ArrayList<String> path = new ArrayList<>();
		Airport start = null;
		Airport goal = null;
		
		if(airports.containsKey(origin))
			start = airports.get(origin);
		if(airports.containsKey(destination))
			goal = airports.get(destination);	
		
		
		if(start == null || goal == null)
		{
			return new BestPath();
		}
		
		//only add after we verify that there is a origin and destination in our graph
		path.add(start.toString());
		
		PriorityQueue<Airport> pq = new PriorityQueue<>();
		
		//set start cost to 0 and add to the priority queue
		start.setCost(0);
		pq.add(start);
		
		
		while(!pq.isEmpty()){
			
			Airport current = pq.remove();
			current.setVisited();
			adjustedAirports.add(start);
			
			if(current.equals(goal)){
				double pathLength = current.cost();
				while(current.previous() != null){
					path.add(1, current.toString());
					current = current.previous();
				}
				
				resetAdjustedAirports(adjustedAirports);
				
				BestPath bp = new BestPath(path, pathLength);
				pq.clear();
				return bp;
			}
			
			//null indicates we don't care about what airliner we are using
			//method is used to add any connecting airports to the PQ from our current airport
			//if the cost to get there is the smallest value
			addNeighbors(criteria, adjustedAirports, pq, current, null);
		}
		
		//reset values so existing graph can be used again
		resetAdjustedAirports(adjustedAirports);
		pq.clear();
		return new BestPath();
	}

	

	/**
	 * <p>
	 * This overloaded method should do the same as the one above only when looking for paths skip the ones that don't
	 * match the given airliner.
	 * </p>
	 * 
	 * @param origin
	 *            - The starting location to find a path from. This should be a 3 character long string denoting an
	 *            airport.
	 * 
	 * @param destination
	 *            - The destination location from the starting airport. Again, this should be a 3 character long string
	 *            denoting an airport.
	 * 
	 * @param criteria
	 *            - This enum dictates the definition of "best". Based on this value a path should be generated and
	 *            return.
	 * 
	 * @param airliner
	 *            - a string dictating the airliner the user wants to use exclusively. Meaning no flights from other
	 *            airliners will be considered.
	 * 
	 * @return - An object containing path information including origin, destination, and everything in between.
	 */
	public BestPath getBestPath(String origin, String destination, FlightCriteria criteria, String airliner) {
		
		ArrayList<Airport> adjustedAirports = new ArrayList<Airport>();
		
		if(origin == null || destination == null){
			ArrayList<String> al = new ArrayList<>();
			al.add(origin);
			al.add(destination);
			return new BestPath(al, 0);
		}
		
		ArrayList<String> path = new ArrayList<>();
		Airport start = null;
		Airport goal = null;
		
		if(airports.containsKey(origin))
			start = airports.get(origin);
		if(airports.containsKey(destination))
			goal = airports.get(destination);	
		
		
		if(start == null || goal == null)
		{
			return new BestPath();
		}
		
		//only add after we verify that there is a origin and destination in our graph
		path.add(start.toString());
		
		PriorityQueue<Airport> pq = new PriorityQueue<>();
		
		//set start cost to 0
		start.setCost(0);
		
		//add starting airport to pq, mark as visited
		pq.add(start);
		
		
		while(!pq.isEmpty()){
			
			Airport current = pq.remove();
			current.setVisited();
			adjustedAirports.add(start);
			
			if(current.equals(goal)){
				double pathLength = current.cost();
				while(current.previous() != null){
					path.add(1, current.toString());
					current = current.previous();
				}
				
				resetAdjustedAirports(adjustedAirports);
				
				BestPath bp = new BestPath(path, pathLength);
				pq.clear();
				return bp;
			}
			
			//method is used to add any connecting airports to the PQ from our current airport
			//if the cost to get there is the smallest value. Filters out connections that
			//don't use the airliner.
			addNeighbors(criteria, adjustedAirports, pq, current, airliner);
		}
		
		//reset values so existing graph can be used again
		resetAdjustedAirports(adjustedAirports);
		pq.clear();
		return new BestPath();
	}

	
	/**
	 * 
	 * Helper method. 
	 * Used to evaluate if a connecting airport is going to be entered into the priority queue
	 * By evaluating if the airport has already been visited in our path and if the cost to
	 * get there is less than the cost it's taken a different path to get there.
	 * If airliner is passed, only neighbors that are attached in the graph by a specific
	 * airline carrier will be added. If airliner is irrelevant and passed as a null, then
	 * it will not filter neighbors by airliner. 
	 * 
	 * @param criteria - 	   - the criteria that determines our shortest path
	 * @param adjustedAirports - a collection of airports we've made adjustments to, so we can reset
	 * @param pq			   - the priority queue we're working on
	 * @param current		   - the current airport that's been dequeued
	 * @param airliner		   - a filtering parameter that forces travel through a specific carrier, set to null if carrier is irrelevant
	 */
	private void addNeighbors(FlightCriteria criteria, ArrayList<Airport> adjustedAirports, PriorityQueue<Airport> pq,
			Airport current, String airliner) {
		
		String resetAirliner = airliner;
		for(Airport neighbor : current.connections()){

			//if the neighboring airport hasn't been popped yet, check if a better path to it exists
			if(!neighbor.visited()){
				Flight flight = flightHash.get(current.toString()+ ',' + neighbor.toString());
				
				//if airliner is null, set airliner to the first carrier in the flight, which the next condition will verify is present
				if(resetAirliner == null)
				{
					airliner = flight.getCarrier().get(0);
				}
				
				if(neighbor.cost() > current.cost() + flight.getWeight(criteria.name()) && flight.getCarrier().contains(airliner)){
					
					neighbor.setPrevious(current);
					neighbor.setCost(current.cost() + flight.getWeight(criteria.name()));
					
					//if neighbor is already in pq due to another path
					//and the new cost is lower, remove it from queue, 
					//and then reinsert to reposition to potential better priority
					if(pq.contains(neighbor))
					{
						pq.remove(neighbor);							
					}
					pq.add(neighbor);
					
					//marks neighbor as having adjusted values in order to reset
					if(!adjustedAirports.contains(neighbor)){
						adjustedAirports.add(neighbor);
					}
				}
			}
		}
	}

	
	
	private void resetAdjustedAirports(ArrayList<Airport> adjustedAirports) {
		for(Airport airport : adjustedAirports){
			airport.setCost(Double.MAX_VALUE);
			airport.setNotVisited();
			airport.setPrevious(null);
		}
	}


	/**
	 * Adds a flight to a hashmap for flights. If the flight path (origin to destination) already exists, it adds the
	 * flights together. See Flight.addFlights(Flight inputFlight) method.
	 * 
	 * @param inputFlight
	 *            - the new flight we are attempting to store.
	 * @throws Exception
	 *             if we attempt to add two flights with mismatched flight paths.
	 */
	private void flightHashAdd(Flight inputFlight) throws Exception {
		String key = inputFlight.destinationString();
		// System.out.println(key);

		if (flightHash.get(key) == null) {
			flightHash.put(key, inputFlight);
		}
		else {
			Flight origFlight = flightHash.get(key);
			// if we are adding a new flight that has an existing origin to destination flight, print these so I can see
			// the path
			origFlight.addFlights(inputFlight);
		}

	}

	
	/**
	 * Used to generate a dot graph to see if the airport/flight graph is built correctly.
	 * @param dotFilename 
	 * 		- the filename the dot graph will be saved as
	 * @param attr 	
	 *		- the flight criteria. this value will be applied as a label to the dot graph and rounded
	 *		to two decimal places for readibility
	 * @param carriers
	 * 		- pass as either a valid Carrier name or pass as null. if passed as null, all flights will
	 * 		be labeled with the weighted cost. if passed with a valid flight carrier in the network the
	 * 		label will contain the cost plus the carrier. only flights with that carrier will be labeled.
	 * 
	 */
	public void airportsToDot(String dotFilename, FlightCriteria attr, String carriers) {
		try (PrintWriter out = new PrintWriter(dotFilename)) {

			ArrayList<Airport> getAirports = new ArrayList<Airport>();
			String ret;
			String label = "";
			out.println("digraph airports {\n\tnode [shape=record]\n");

			getAirports.addAll(airports.values());

			for (Airport airport : getAirports) {
				ret = "\t" + airport.toString() + " [label = \"<f1> " + airport.toString() + " \"]\n";
				for (Airport destination : airport.getDestinations()) {
					Flight flight = flightHash.get(airport.toString() + ',' + destination.toString());
					double weight = flight.getWeight(attr.toString());
					DecimalFormat f = new DecimalFormat("#.00");
					
					if(carriers != null)
					{
						if(flight.getCarrier().contains(carriers))
						{
							label = ""+f.format(weight) + ' ' + carriers;
						}
						else
						{
							label = "";
						}
					}
					else{
						label = ""+f.format(weight);
					}
					ret += airport.toString() + " -> " + destination.toString() + ":f1" + "[label=\"" + label + "\"]" + '\n';
				}
				out.println(ret);
				
			}

			out.println("}");
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		NetworkGraph ng = new NetworkGraph("CSV_Files/test10x15.csv");
		ng.airportsToDot("test10x15-time.dot", FlightCriteria.TIME, null);
	}
	
}