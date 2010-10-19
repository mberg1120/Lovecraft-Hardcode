package com.lovecraft;

import java.util.Vector;

public class Room extends GameObject {
	
	Vector roomInventory = new Vector();
	Inventory room = new Inventory();

	@Override
	public boolean useObject() {
		
		// use object will reflect what happens when you move from room
		// to room or drop an object... or even use an object with another
		// xml should be able to hold a combination of objects and what happens
		// when the objects are used with each other.
		
		return false;
	}
	
	public void roomContents() {
		getName();
		System.out.println("Contents of " + objectName + ":");
		for (int i = 0; i < roomInventory.size(); i++) {
			System.out.println("	" + "-" + roomInventory.get(i));
		}
	}

}
