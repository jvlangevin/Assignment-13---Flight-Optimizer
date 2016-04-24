package assignment13;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class NetworkGraphTest {

	@Test
	public void test5Nodes10Edges() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test5x10.csv");
		
		BestPath bp = test.getBestPath("OKC", "NQP", FlightCriteria.COST);
		
		/*Print details if needed */
		//System.out.println(bp.toString());
		
		assertEquals("[OKC, PRP, CGY, NQP]", bp.getPath().toString());
		assertEquals(1004.0166666666667, bp.getPathLength(), 1e-6);
	}

}
