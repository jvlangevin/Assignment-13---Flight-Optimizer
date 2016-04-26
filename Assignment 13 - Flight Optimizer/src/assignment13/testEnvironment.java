package assignment13;

import java.io.FileNotFoundException;


/**
 * A generic environment to test code as it's implemented
 * @author Nathan
 *
 */
public class testEnvironment {

	public static void main(String[] args) throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		
		
		/*USED TO GENERATE A DOT FILE WITH VALUES BASED ON FLIGHTCRITERIA*/
		/*
		test.airportsToDot("DotFiles/test10x25.dot", FlightCriteria.COST, null);
		BestPath shortestDistancePath = test.getBestPath("WWL", "YVT", FlightCriteria.COST);
		System.out.println(shortestDistancePath.toString());*/

		
		
		/*USED IF YOU WANT TO A DOT FILE WITH A SPECIFIC CARRIER IN MIND
		  VALUES WILL ONLY DISPLAY IF THAT CARRIER EXISTS ON THE FLIGHT PATH
		  VALUES PRINTED DEPEND ON THE FLIGHT CRITERIA PASSED*/
		
		NetworkGraph test2 = new NetworkGraph("CSV_Files/test10x100.csv");
		test2.airportsToDot("DotFiles/test10x100-carriers-cost.dot", FlightCriteria.COST, "HA");
		//BestPath shortestDistancePath2 = test2.getBestPath("WWL", "HIA", FlightCriteria.CO, "UA");
		//System.out.println(shortestDistancePath2.toString());
		
		
	}

}
