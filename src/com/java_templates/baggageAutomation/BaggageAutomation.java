package com.java_templates.baggageAutomation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class BaggageAutomation {
	private static List<Departure>	departureList	= new ArrayList<>();
	private static List<Bag>		bagList			= new ArrayList<>();
	
	public static void main(String... args) {
		System.out.println("Please enter inputs . Input # at new line when finised");
		//Scanner scanner = new Scanner(System.in);
		Scanner scanner = new Scanner("# Section: Conveyor System\n" + 
				"Concourse_A_Ticketing A5 5\n" + 
				"A5 BaggageClaim 5\n" + 
				"A5 A10 4\n" + 
				"A5 A1 6\n" + 
				"A1 A2 1\n" + 
				"A2 A3 1\n" + 
				"A3 A4 1\n" + 
				"A10 A9 1\n" + 
				"A9 A8 1\n" + 
				"A8 A7 1\n" + 
				"A7 A6 1\n" + 
				"# Section: Departures\n" + 
				"UA10 A1 MIA 08:00\n" + 
				"UA11 A1 LAX 09:00\n" + 
				"UA12 A1 JFK 09:45\n" + 
				"UA13 A2 JFK 08:30\n" + 
				"UA14 A2 JFK 09:45\n" + 
				"UA15 A2 JFK 10:00\n" + 
				"UA16 A3 JFK 09:00\n" + 
				"UA17 A4 MHT 09:15\n" + 
				"UA18 A5 LAX 10:15\n" + 
				"# Section: Bags\n" + 
				"0001 Concourse_A_Ticketing UA12\n" + 
				"0002 A5 UA17\n" + 
				"0003 A2 UA10\n" + 
				"0004 A8 UA18\n" + 
				"0005 A7 ARRIVAL\n" + 
				"#");
		Graph graph = new Graph();
		while (!scanner.nextLine().startsWith("#"));
		// Section 1 Input
		while (scanner.hasNextLine()) {
			String input = scanner.nextLine();
			if (input.startsWith("#"))
				break;
			String[] parts = input.split(" ");
			Node node1 = graph.getNodeByName(parts[0]);
			Node node2 = graph.getNodeByName(parts[1]);
			int time = Integer.parseInt(parts[2]);
			node1.addDestination(node2, time);
		}
		// Section 2 Input
		while (scanner.hasNextLine()) {
			String input = scanner.nextLine();
			if (input.startsWith("#"))
				break;
			String[] parts = input.split(" ");
			Node flightGate = graph.getNodeByName(parts[1]);
			Departure departure = new Departure(parts[0], flightGate, parts[2], parts[3]);
			departureList.add(departure);
		}
		// Section 3 Input
		while (scanner.hasNextLine()) {
			String input = scanner.nextLine();
			if (input.startsWith("#"))
				break;
			String[] parts = input.split(" ");
			Node entryPoint = graph.getNodeByName(parts[1]);
			Node destination;
			if (parts[2].equalsIgnoreCase("ARRIVAL"))
				destination = graph.getNodeByName("BaggageClaim");
			else destination = getDepartureByFlightId(parts[2]).getFlightGate();
			Bag bag = new Bag(parts[0], entryPoint, parts[2], destination);
			bagList.add(bag);
		}
		scanner.close();
		for (Bag bag : bagList) {
			String pathShort = calculateShortestPathFromSourceToDestination(bag.getEntryPoint(), bag.getDestination());
			System.out.println(bag.getBagNumber() + " " + pathShort);
			graph.reset();
		}
	}
	
	public static Departure getDepartureByFlightId(String flightId) {
		for (Departure departure : departureList) {
			if (departure.getFlightId().equalsIgnoreCase(flightId))
				return departure;
		}
		return null;
	}
	
	public static String calculateShortestPathFromSourceToDestination(Node source, Node destination) {
		source.setTime(0);
		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();
		unsettledNodes.add(source);
		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestTimeNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumTime(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		List<Node> path = destination.getShortestPath();
		path.add(destination);
		String response = "";
		Integer totalTime = destination.getTime();
		for (Node node : path) {
			response = String.join(" ", response, node.getName());
		}
		return String.join(" : ", response, totalTime.toString()).trim();
	}
	
	private static Node getLowestTimeNode(Set<Node> unsettledNodes) {
		Node lowestTimeNode = null;
		int lowestTime = Integer.MAX_VALUE;
		for (Node node : unsettledNodes) {
			int nodeTime = node.getTime();
			if (nodeTime < lowestTime) {
				lowestTime = nodeTime;
				lowestTimeNode = node;
			}
		}
		return lowestTimeNode;
	}
	
	private static void calculateMinimumTime(Node evaluationNode,
	        Integer edgeWeigh, Node sourceNode) {
		Integer sourceTime = sourceNode.getTime();
		if (sourceTime + edgeWeigh < evaluationNode.getTime()) {
			evaluationNode.setTime(sourceTime + edgeWeigh);
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
}
