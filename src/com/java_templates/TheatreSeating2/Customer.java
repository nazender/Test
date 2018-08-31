package com.java_templates.TheatreSeating2;

public class Customer implements Comparable<Customer> {
	private final String	name;
	private final int		ticketReq;
	private boolean			split;
	private String			seatInfo;
	
	public Customer(String name, int ticketReq) {
		this.name = name;
		this.ticketReq = ticketReq;
	}
	
	public int getTicketReq() {
		return ticketReq;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.join(",", ("{\"name\":\"" + name+"\""), seatInfo + "}");
	}
	
	public boolean isSplit() {
		return split;
	}
	
	public void setSplit(boolean split) {
		this.split = split;
	}
	
	@Override
	public int compareTo(Customer o) {
		return this.ticketReq - o.ticketReq;
	}
	
	public String getSeatInfo() {
		return seatInfo;
	}
	
	public void setSeatInfo(String seatInfo) {
		this.seatInfo = seatInfo;
	}
}
