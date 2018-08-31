package com.java_templates.TheatreSeating2;

import java.util.ArrayList;

public class Row {
	ArrayList<Section>		sections;
	private final String	name;
	
	Row(String name) {
		this.name = name;
		sections = new ArrayList<>();
	}
	
	public ArrayList<Section> getSections() {
		return sections;
	}
	
	public void addSection(Section section) {
		sections.add(section);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "\"row\":" + this.name.split(" ")[1];
	}
}
