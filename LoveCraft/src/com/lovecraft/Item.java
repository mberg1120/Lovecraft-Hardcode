package com.lovecraft;

import java.util.Vector;

public class Item extends GameObject {
	
	Vector inventory = new Vector();

	@Override
	public boolean useObject() {
		
		// logic needed to answer:
		// can the combination of objects be used together?
		
		return false;
	}
	
	public String pickUpItem() {
		getName();
		inventory.add(objectName);
		return objectName;
	}
	
	public void inventoryContents() {
		System.out.println("Contents of Inventory: ");
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println("	" + "-" + inventory.get(i));
		}
	}
	
	public String dropItem() {
		getName();
		inventory.remove(inventory.indexOf(objectName));
		// TODO add dropped object to current room
		return objectName;
	}

}
