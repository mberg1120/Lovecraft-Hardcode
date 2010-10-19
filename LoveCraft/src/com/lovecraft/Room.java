package com.lovecraft;

import java.util.Vector;

public class Room extends GameObject {
	
	Vector roomContents = new Vector();

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
		for (int i = 0; i < roomContents.size(); i++) {
			System.out.println("	" + "-" + roomContents.get(i));
		}
	}

}
