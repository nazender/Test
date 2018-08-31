package com.java_templates.TheatreSeating;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Theatre {
	private ArrayList<Row> rows;

	public Theatre() {
		rows = new ArrayList<>();
	}

	public int getTotalSeatsAvailable() {
		int response = 0;
		for (Row row : rows) {
			for (Section section : row.getSections()) {
				response += section.getSeatCount();
			}
		}
		return response;
	}

	public ArrayList<Row> getRows() {
		return rows;
	}

	public void addRow(Row row) {
		this.rows.add(row);
	}

	@Override
	public String toString() {
		return "Theatre :" + rows;
	}

	public int getMaxSingleSeatingCount() {
		int response = 0;
		for (Row row : rows) {
			for (Section section : row.getSections()) {
				if (section.getSeatCount() > response)
					response = section.getSeatCount();
			}
		}
		return response;
	}

	public void allotSeats(List<Customer> customers) {
		for (Row row : rows) {
			Section: for (Section section : row.getSections()) {
				int seatsAvailable = section.getSeatCount();
				Customer cuurentAllocation = null;
				Collections.sort(customers);

				//Single customer single section
				for (Customer customer : customers) {
					if (customer.getTicketReq() == seatsAvailable) {
						customer.setSeatInfo(String.join(" ", row.getName(), section.getName()));
						section.decreaseSeats(customer.getTicketReq());
						cuurentAllocation = customer;
						break;
					}
				}
				int customerSize = customers.size();
				if (cuurentAllocation != null) {
					customers.remove(cuurentAllocation);
				}

				//double customer single section
				else {
					int left = 0, right = customerSize - 1;
					while (left < right) {
						int move = customers.get(left).getTicketReq() + customers.get(right).getTicketReq() - seatsAvailable;
						if (move == 0) {
							Customer customer1 = customers.get(left);
							Customer customer2 = customers.get(right);
							customer1.setSeatInfo(String.join(" ", row.getName(), section.getName()));
							customer2.setSeatInfo(String.join(" ", row.getName(), section.getName()));
							section.decreaseSeats(customer1.getTicketReq() + customer2.getTicketReq());
							customers.remove(customer1);
							customers.remove(customer2);
							continue Section;
						} else if (move < 0) {
							left++;
						} else
							right--;
					}

					if (left != customerSize - 1) {// then triplets are possible
						//triple customer single section
						for (int i = 0; i < customerSize; i++) {
							int iTickets = customers.get(i).getTicketReq();
							for (int j = i + 1; j < customerSize; j++) {
								int jTickets = customers.get(j).getTicketReq();
								for (int k = j + 1; k < customerSize; k++) {
									int kTickets = customers.get(k).getTicketReq();
									if (iTickets + jTickets + kTickets == seatsAvailable) {
										//Success
										Customer customer1 = customers.get(i);
										Customer customer2 = customers.get(j);
										Customer customer3 = customers.get(k);
										customer1.setSeatInfo(String.join(" ", row.getName(), section.getName()));
										customer2.setSeatInfo(String.join(" ", row.getName(), section.getName()));
										customer2.setSeatInfo(String.join(" ", row.getName(), section.getName()));
										section.decreaseSeats(iTickets + jTickets + kTickets);
										customers.remove(customer1);
										customers.remove(customer2);
										customers.remove(customer3);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}