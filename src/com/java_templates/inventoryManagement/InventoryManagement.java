package com.java_templates.inventoryManagement;

import java.util.Scanner;

public class InventoryManagement {
	/**create Book01 10.50 13.79
create Food01 1.47 3.98
create Med01 30.63 34.29
create Tab01 57.00 84.98
updateBuy Tab01 100
updateSell Tab01 2
updateBuy Food01 500
updateBuy Book01 100
updateBuy Med01 100
updateSell Food01 1
updateSell Food01 1
updateSell Tab01 2
report
delete Book01
updateSell Tab01 5
create Mobile01 10.51 44.56
updateBuy Mobile01 250
updateSell Food01 5
updateSell Mobile01 4
updateSell Med01 10
report
  */
	public static void main(String[] args) {
		Scanner scanner = new Scanner("create Book01 10.50 13.79\r\n" + 
				"create Food01 1.47 3.98\r\n" + 
				"create Med01 30.63 34.29\r\n" + 
				"create Tab01 57.00 84.98\r\n" + 
				"updateBuy Tab01 100\r\n" + 
				"updateSell Tab01 2\r\n" + 
				"updateBuy Food01 500\r\n" + 
				"updateBuy Book01 100\r\n" + 
				"updateBuy Med01 100\r\n" + 
				"updateSell Food01 1\r\n" + 
				"updateSell Food01 1\r\n" + 
				"updateSell Tab01 2\r\n" + 
				"report\r\n" + 
				"delete Book01\r\n" + 
				"updateSell Tab01 5\r\n" + 
				"create Mobile01 10.51 44.56\r\n" + 
				"updateBuy Mobile01 250\r\n" + 
				"updateSell Food01 5\r\n" + 
				"updateSell Mobile01 4\r\n" + 
				"updateSell Med01 10\r\n" + 
				"report\r\n");
		//Scanner scanner1 = new Scanner(System.in);// Use this one For console input
		Inventory inventory = new Inventory();
		while (scanner.hasNextLine()) {
			//System.out.println("Please enter new command");
			String command = scanner.nextLine();
			String[] parts = command.split(" ");
			try {
				switch (parts[0]) {
				case "create":
					if (parts.length != 4 || !parts[2].matches("^\\d+(\\.\\d+)?$") || !parts[3].matches("^\\d+(\\.\\d+)?$"))
						throw new RuntimeException("Invalid command. Try Again");
					inventory.addNewItem(new Item(parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
					break;
				case "updateBuy":
					if (parts.length != 3 || !parts[2].matches("^\\d+$"))
						throw new RuntimeException("Invalid command. Try Again");
					inventory.updateBuy(parts[1], Integer.parseInt(parts[2]));
					break;
				case "updateSell":
					if (parts.length != 3 || !parts[2].matches("^\\d+$"))
						throw new RuntimeException("Invalid command. Try Again");
					inventory.updateSell(parts[1], Integer.parseInt(parts[2]));
					break;
				case "delete":
					if (parts.length != 2)
						throw new RuntimeException("Invalid command. Try Again");
					inventory.deleteItem(parts[1]);
					break;
				case "report":
					if (parts.length != 1)
						throw new RuntimeException("Invalid command. Try Again");
					inventory.report();
					break;
				case "exit":
					scanner.close();
					System.exit(0);
				default:
					throw new RuntimeException("Invalid command. Try Again");
				}
			} catch (RuntimeException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
