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
	
	@Test
	public void test5Nodes15Edges() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test5x15.csv");
		
		BestPath bp = test.getBestPath("XPT", "BGA", FlightCriteria.COST);
		
		assertEquals("[XPT, MCG, KZF, BGA]", bp.getPath().toString());
		assertEquals(1172.81, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void test5Nodes20Edges() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test5x20.csv");
		
		BestPath bp = test.getBestPath("JIV", "AFR", FlightCriteria.COST);
		
		assertEquals("[JIV, HFI, LUS, AFR]", bp.getPath().toString());
		assertEquals(897.70, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void test10Nodes15Edges() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x15.csv");
		
		BestPath bp = test.getBestPath("AKC", "JKW", FlightCriteria.COST);
		
		assertEquals("[AKC, IRL, JUA, JKW]", bp.getPath().toString());
		assertEquals(1899.03, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void test10Nodes25Edges() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		BestPath bp = test.getBestPath("ZQZ", "KKS", FlightCriteria.COST);
		
		assertEquals("[ZQZ, FHJ, NRW, PZL, KKS]", bp.getPath().toString());
		assertEquals(1480.52, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void noValidPathFromOriginToDestination() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		BestPath bp = test.getBestPath("CDV", "JAP", FlightCriteria.COST);
		
		assertEquals("[]", bp.getPath().toString());
		assertEquals(0, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void originAirportIsNull() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		BestPath bp = test.getBestPath(null, "JAP", FlightCriteria.COST);
		
		assertEquals("[null, JAP]", bp.getPath().toString());
		assertEquals(0, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void destinationAirportIsNull() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		BestPath bp = test.getBestPath("JAP", null, FlightCriteria.COST);
		
		assertEquals("[JAP, null]", bp.getPath().toString());
		assertEquals(0, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void originAndDestinationAreNull() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		BestPath bp = test.getBestPath(null, null, FlightCriteria.COST);
		
		assertEquals("[null, null]", bp.getPath().toString());
		assertEquals(0, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void originAirportNotInGraph() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		BestPath bp = test.getBestPath("QVC", "JAP", FlightCriteria.COST);
		
		assertEquals("[]", bp.getPath().toString());
		assertEquals(0, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void destinationAirportNotInGraph() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		BestPath bp = test.getBestPath("JAP", "BNO", FlightCriteria.COST);
		
		assertEquals("[]", bp.getPath().toString());
		assertEquals(0, bp.getPathLength(), 0.01);
	}
	
	@Test
	public void originAndDestinationNotInGraph() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");
		
		BestPath bp = test.getBestPath("QVC", "BNO", FlightCriteria.COST);
		
		assertEquals("[]", bp.getPath().toString());
		assertEquals(0, bp.getPathLength(), 0.01);
	}
	
	
}
