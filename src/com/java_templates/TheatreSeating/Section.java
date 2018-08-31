package com.java_templates.TheatreSeating;
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
		return this.name + "(" + seatCount + ")";
	}

	public void decreaseSeats(int count) {
		seatCount -= count;
	}

}

