package assignment13;

import java.io.FileNotFoundException;

public class testEnvironment {

	public static void main(String[] args) throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("flights-2015-q3.csv");
		BestPath shortestDistancePath = test.getBestPath("WWL", "YVT", FlightCriteria.COST);
		System.out.println(shortestDistancePath.toString());

		NetworkGraph test2 = new NetworkGraph("flights-2015-q3.csv");
		BestPath shortestDistancePath2 = test2.getBestPath("WWL", "HIA", FlightCriteria.COST, "UA");
		System.out.println(shortestDistancePath2.toString());
	}

}
