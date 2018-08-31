package com.java_templates.TheatreSeating;
import java.util.ArrayList;
import java.util.Scanner;

public class TheatreSeating {
	/** 6 6
	 * 3 5 5 3
	 * 4 6 6 4
	 * 2 8 8 2
	 * 6 6
	 * Smith 2
	 * Jones 5
	 * Davis 6
	 * Wilson 100
	 * Johnson 3
	 * Williams 4
	 * Brown 8
	 * Miller 12 */

	public static void main(String[] args) {
		System.out.println("Enter theatre layout and Ticket requests");
		Scanner scanner = new Scanner("6 6\r\n" + "3 5 5 3\r\n" + "4 6 6 4\r\n" + "2 8 8 2\r\n" + "6 6\r\n" + " \r\n" + "Smith 2\r\n" + "Jones 5\r\n" + "Davis 6\r\n" + "Wilson 100\r\n"
				+ "Johnson 3\r\n" + "Williams 4\r\n" + "Brown 8\r\n" + "Miller 12");
		//Scanner scanner = new Scanner(System.in);// Use this one For console input
		boolean isTheatreLayout = true;
		Theatre theatre = new Theatre();
		ArrayList<Customer> customers = new ArrayList<>();
		int rowCount = 0;
		while (scanner.hasNextLine()) {
			String next = scanner.nextLine();
			if (isTheatreLayout) {
				if (next.trim().isEmpty()) {
					isTheatreLayout = false;
					continue;
				}
				String[] rowsString = next.split(" ");
				Row row = new Row("Row " + (++rowCount));
				for (int i = 0; i < rowsString.length; i++) {
					row.addSection(new Section("Section " + (i + 1), Integer.parseInt(rowsString[i])));
				}
				theatre.addRow(row);
			} else {
				String[] requestString = next.split(" ");
				if (requestString.length < 2)
					break;
				customers.add(new Customer(requestString[0], Integer.parseInt(requestString[1])));
			}
		}
		theatre.allotSeats(new ArrayList<>(customers));
		int totalSeats = theatre.getTotalSeatsAvailable();
		int singleSectioncount = theatre.getMaxSingleSeatingCount();
		for (Customer customer : customers) {
			if (customer.getTicketReq() > totalSeats) {
				customer.setSeatInfo("Sorry, we can't handle your party");
			} else if (customer.getTicketReq() > singleSectioncount) {
				customer.setSeatInfo("Call to split party.");
			}
		}
		scanner.close();
		System.out.println(theatre);
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}
}
