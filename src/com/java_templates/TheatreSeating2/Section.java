package com.java_templates.TheatreSeating2;
public class Section {
	private int seatCount;
	private final String name;

	Section(String name, int seatCount) {
		this.name = name;
		this.seatCount = seatCount;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "\"section\":" + this.name.split(" ")[1];
	}

	public void decreaseSeats(int count) {
		seatCount -= count;
	}

}

