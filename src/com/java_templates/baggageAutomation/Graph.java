package com.java_templates.baggageAutomation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Graph {
	private Set<Node> nodes = new HashSet<>();
	
	public void addNode(Node nodeA) {
		nodes.add(nodeA);
	}
	
	public Set<Node> getNodes() {
		return nodes;
	}
	
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
	
	public Node getNodeByName(String name) {
		Node response = null;
		for (Node node : nodes)
			if (node.getName().equalsIgnoreCase(name)) {
				response = node;
				break;
			}
		if (response == null) {
			response = new Node(name);
			this.addNode(response);
		}
		return response;
	}
	
	public void reset() {
		for (Node node : nodes) {
			node.setTime(Integer.MAX_VALUE);
			node.setShortestPath(new LinkedList<>());
		}
	}
}
