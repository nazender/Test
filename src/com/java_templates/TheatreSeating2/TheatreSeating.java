package com.java_templates.TheatreSeating2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheatreSeating {
	public static void main(String[] args) {
		System.out.println("Enter theatre layout and Ticket requests");
		String jsonInputFowTheatreLayout = "[\r\n" +
		        "{row: [ {“section”:6},{“section”:6}]},\r\n" +
		        "{row: [ {“section”:3},{“section”:5},{“section”:5},{“section”:3}]},\r\n" +
		        "{row: [ {“section”:4},{“section”:6},{“section”:6},{“section”:4}]} ,\r\n" +
		        "{row: [ {“section”:2},{“section”:8},{“section”:8},{“section”:2}]} , row: [ {“section”:6},{“section”:6}]}\r\n" +
		        "]";
		String jsonInputForCustomers = "[\r\n" +
		        "{“seq”:1, “name”: “smith”, “tickets”: 2},\r\n" +
		        "{“seq”:2, “name”: “jones”, “tickets”: 5},\r\n" +
		        "{“seq”:3, “name”: “davis”, “tickets”: 6},\r\n" +
		        "{“seq”:4, “name”: “wilson”, “tickets”: 100},\r\n" +
		        "{“seq”:5, “name”: “johnson”, “tickets”: 3},\r\n" +
		        "{“seq”:6, “name”: “williams”, “tickets”: 4},\r\n" +
		        "{“seq”:7, “name”: “brown”, “tickets”: 8},\r\n" +
		        "{“seq”:8, “name”: “miller”, “tickets”: 12},\r\n" +
		        "]";
		Theatre theatre = getTheatreLayout(jsonInputFowTheatreLayout);
		List<Customer> customers = getCustomersFromJson(jsonInputForCustomers);
		theatre.allotSeats(new ArrayList<>(customers));
		int totalSeats = theatre.getTotalSeatsAvailable();
		int singleSectioncount = theatre.getMaxSingleSeatingCount();
		for (Customer customer : customers) {
			if (customer.getTicketReq() > totalSeats) {
				customer.setSeatInfo("\"message\":\"Sorry, we can't handle your party\"");
			} else if (customer.getTicketReq() > singleSectioncount) {
				customer.setSeatInfo("\"message\":\"Call to split party.\"");
			}
		}
		String jsonOutput = "";
		for (Customer customer : customers) {
			jsonOutput = String.join(",", jsonOutput, customer.toString());
		}
		jsonOutput = jsonOutput.replaceFirst(",", "[") + "]";
		System.out.println(jsonOutput);
	}
	
	private static List<Customer> getCustomersFromJson(String jsonInputForCustomers) {
		List<Customer> customers = new ArrayList<>();
		Matcher matcher = Pattern.compile("\\{(.*?)\\}").matcher(jsonInputForCustomers);
		while (matcher.find()) {
			String customerJson = matcher.group(1);
			String[] pairs = customerJson.split(",");
			String customerName = "";
			String tickets = "0";
			for (String pair : pairs) {
				if (pair.contains("name")) {
					String name = pair.split(":")[1].trim();
					customerName = name.substring(1, name.length() - 1);
				} else if (pair.contains("tickets"))
					tickets = pair.split(":")[1].trim();
			}
			customers.add(new Customer(customerName, Integer.parseInt(tickets)));
		}
		return customers;
	}
	
	public static Theatre getTheatreLayout(String jsonInput) {
		jsonInput = jsonInput.trim();
		if (!(jsonInput.startsWith("[") && jsonInput.endsWith("]")))
			throw new RuntimeException("Invalid theatre layout input");
		Pattern rowPattern = Pattern.compile("\\{\\s*?row\\s*?:\\s*?\\[.*?\\]\\s*?\\}");
		Pattern secPattern = Pattern.compile("\\{\\s*?.section.\\s*?:\\s*?(\\d*?)\\s*?\\}");
		Matcher rowMatcher = rowPattern.matcher(jsonInput);
		Theatre theatre = new Theatre();
		int rowCount = 0;
		while (rowMatcher.find()) {
			int secCount = 0;
			String rowJson = rowMatcher.group();
			Matcher secMatcher = secPattern.matcher(rowJson);
			Row row = new Row("Row " + (++rowCount));
			while (secMatcher.find()) {
				int seats = Integer.parseInt(secMatcher.group(1));
				row.addSection(new Section("Section " + (++secCount), seats));
			}
			theatre.addRow(row);
		}
		return theatre;
	}
}
