package com.lovecraft;

import java.util.Vector;

public class Item extends GameObject {
	Inventory playerInventory = new Inventory();
	protected boolean itemInRoom = true;

	@Override
	public boolean useObject() {

		// logic needed to answer:
		// can the combination of objects be used together?

		return false;
	}
	
	public void pickUpItem(){
		if (itemInRoom == true) {
			playerInventory.addItemToInventory(objectName);
			itemInRoom = false;
		}
	}
	
	public void dropItem() {
		if (itemInRoom != true) {
			playerInventory.removeItemFromInventory(objectName);
			itemInRoom = true;
		}
	}

}
