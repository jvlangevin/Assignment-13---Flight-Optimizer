package assignment13;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import org.junit.Test;

public class NetworkGraphTest {

	@Test
	public void test5Nodes10Edges() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test5x10.csv");

		BestPath bp = test.getBestPath("OKC", "NQP", FlightCriteria.COST);

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

	@Test
	public void test10Nodes100EdgesCarrier() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x100.csv");

		BestPath bp = test.getBestPath("GOO", "KUE", FlightCriteria.CANCELED, "HA");

		/* Print details if needed */
		// System.out.println(bp.toString());

		assertEquals("[GOO, QXR, KUE]", bp.getPath().toString());
		assertEquals(.5, bp.getPathLength(), 1e-6);

		BestPath bp2 = test.getBestPath("GOO", "KUE", FlightCriteria.COST, "HA");
		assertEquals("[GOO, QXR, KUE]", bp2.getPath().toString());
		assertEquals(701.09, bp2.getPathLength(), 1e-6);
	}

	@Test
	public void test5Nodes20EdgesCarriersNoPath() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test5x20.csv");

		BestPath bp = test.getBestPath("JIV", "AFR", FlightCriteria.COST, "UA");

		assertEquals("[]", bp.getPath().toString());
		assertEquals(0.0, bp.getPathLength(), 0.01);
	}

	@Test
	public void test5Nodes20EdgesCarriers() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test5x20.csv");

		BestPath bp = test.getBestPath("TOL", "AFR", FlightCriteria.COST, "UA");

		assertEquals("[TOL, LUS, AFR]", bp.getPath().toString());
		assertEquals(527.055, bp.getPathLength(), 0.01);
	}

	@Test
	public void test10Nodes25EdgesCarriers() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x25.csv");

		BestPath bp = test.getBestPath("NTR", "ZQZ", FlightCriteria.CANCELED, "HA");

		assertEquals("[NTR, DBF, QZR, ZQZ]", bp.getPath().toString());
		assertEquals(1.5, bp.getPathLength(), 0.01);
	}

	@Test
	public void test10Nodes15EdgesCarriers() throws FileNotFoundException {

		// *Path with flight MQ - exists, is long
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x15.csv");

		BestPath bp = test.getBestPath("XCB", "WBC", FlightCriteria.COST, "MQ");

		assertEquals("[XCB, JUA, JKW, WBC]", bp.getPath().toString());
		assertEquals(2754.00, bp.getPathLength(), 0.01);

		// Path with flight AA - exists, is direct
		NetworkGraph test2 = new NetworkGraph("CSV_Files/test10x15.csv");

		BestPath bp2 = test2.getBestPath("XCB", "WBC", FlightCriteria.COST, "AA");

		assertEquals("[XCB, WBC]", bp2.getPath().toString());
		assertEquals(266.96, bp2.getPathLength(), 0.01);

		// Path with flight DL - doesn't exist
		NetworkGraph test3 = new NetworkGraph("CSV_Files/test10x15.csv");

		BestPath bp3 = test3.getBestPath("XCB", "WBC", FlightCriteria.COST, "DL");

		assertEquals("[]", bp3.getPath().toString());
		assertEquals(0.0, bp3.getPathLength(), 0.01);
	}

	@Test
	public void getBestPathAllFlightCriteria() throws FileNotFoundException {
		NetworkGraph test = new NetworkGraph("CSV_Files/test10x15.csv");

		BestPath bpCost = test.getBestPath("AKC", "JKW", FlightCriteria.COST);
		assertEquals("[AKC, IRL, JUA, JKW]", bpCost.getPath().toString());
		assertEquals(1899.03, bpCost.getPathLength(), 0.01);

		BestPath bpDelay = test.getBestPath("AKC", "JKW", FlightCriteria.DELAY);
		assertEquals("[AKC, IRL, JUA, JKW]", bpDelay.getPath().toString());
		assertEquals(495, bpDelay.getPathLength(), 1);

		BestPath bpDistance = test.getBestPath("AKC", "JKW", FlightCriteria.DISTANCE);
		assertEquals("[AKC, IRL, JUA, JKW]", bpDistance.getPath().toString());
		assertEquals(5082, bpDistance.getPathLength(), 1);

		BestPath bpCanceled = test.getBestPath("AKC", "JKW", FlightCriteria.CANCELED);
		assertEquals("[AKC, IRL, JUA, JKW]", bpCanceled.getPath().toString());
		assertEquals(1, bpCanceled.getPathLength(), 1);

		BestPath bpTime = test.getBestPath("AKC", "JKW", FlightCriteria.TIME);
		assertEquals("[AKC, IRL, JUA, JKW]", bpTime.getPath().toString());
		assertEquals(735, bpTime.getPathLength(), 1);
	}

}
