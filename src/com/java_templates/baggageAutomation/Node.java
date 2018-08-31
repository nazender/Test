package com.java_templates.baggageAutomation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
	private String		name;
	private List<Node>	shortestPath	= new LinkedList<>();
	private Integer		time			= Integer.MAX_VALUE;
	Map<Node, Integer>	adjacentNodes	= new HashMap<>();
	
	public void addDestination(Node destination, int time) {
		adjacentNodes.put(destination, time);
		destination.getAdjacentNodes().put(this, time);
	}
	
	public Node(String name) {
		this.name = name;
	}
	// getters and setters
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Node> getShortestPath() {
		return shortestPath;
	}
	
	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}
	
	public Integer getTime() {
		return time;
	}
	
	public void setTime(Integer time) {
		this.time = time;
	}
	
	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}
	
	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}
	
	@Override
	public String toString() {
		return "Node [name=" + name + ", time=" + time + "]";
	}
}
