package com.java_templates.baggageAutomation;

public class Bag {
	private String	bagNumber;
	private Node	entryPoint;
	private String	flightId;
	private Node	destination;
	
	public Bag(String bagNumber, Node entryPoint, String flightId, Node destination) {
		super();
		this.bagNumber = bagNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
		this.setDestination(destination);
	}
	
	public String getBagNumber() {
		return bagNumber;
	}
	
	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}
	
	public Node getEntryPoint() {
		return entryPoint;
	}
	
	public void setEntryPoint(Node entryPoint) {
		this.entryPoint = entryPoint;
	}
	
	public String getFlightId() {
		return flightId;
	}
	
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	public Node getDestination() {
		return destination;
	}
	
	public void setDestination(Node destination) {
		this.destination = destination;
	}
}
