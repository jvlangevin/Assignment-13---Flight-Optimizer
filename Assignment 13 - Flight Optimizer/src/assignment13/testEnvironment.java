package assignment13;

import java.io.FileNotFoundException;

public class testEnvironment {

	public static void main(String[] args) throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("flights-2015-q3.csv");
		
		test.flightHashAverage();
		

	}

}
