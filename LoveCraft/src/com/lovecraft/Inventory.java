package com.lovecraft;

import java.util.Vector;

public class Inventory {
	
	Vector inventory = new Vector();
	
	public String addItemToInventory(String objectName) {
		inventory.add(objectName);
		return objectName;
	}
	
	public void inventoryContents() {
		System.out.println("Contents of Inventory: ");
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println("	" + "-" + inventory.get(i));
		}
	}
	
//	public void inventoryPrint(String item) {
//		// find item name in vector
//		// Mike do this you whore
//	}
	
	public String removeItemFromInventory(String objectName) {
		inventory.remove(inventory.indexOf(objectName));
//		create a dropItem method in Item class, needs to add item to current room
		
		return objectName;
	}


}
