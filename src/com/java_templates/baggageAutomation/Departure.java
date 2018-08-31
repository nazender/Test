package com.java_templates.baggageAutomation;

public class Departure {
	private String	flightId;
	private Node	flightGate;
	private String	destination;
	private String	flightTime;
	
	public Departure(String flightId, Node flightGate, String destination, String flightTime) {
		super();
		this.flightId = flightId;
		this.flightGate = flightGate;
		this.destination = destination;
		this.flightTime = flightTime;
	}

	public String getFlightId() {
		return flightId;
	}
	
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	public Node getFlightGate() {
		return flightGate;
	}
	
	public void setFlightGate(Node flightGate) {
		this.flightGate = flightGate;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getFlightTime() {
		return flightTime;
	}
	
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}
}
