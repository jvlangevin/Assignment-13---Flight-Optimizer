package assignment13;

import java.util.ArrayList;

public class Airport {
	
	private String code;
	private boolean visited;
	private ArrayList<Airport> connections;
	
	public Airport(String code){
		this.code = code;
		this.visited = false;
		this.connections = new ArrayList<>();
	}

}
