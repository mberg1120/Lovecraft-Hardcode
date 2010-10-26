package com.lovecraft;
/**
 * 
 * @author Michael
 * @comments Creates an item for future use by the user or the room.
 */

public class Item {

	String itemName;
	String itemDescription;
	String holder;
	String floorDescription ;
	/**
	 * Saves the item, and gives it a physical description. 
	 * @param name
	 * @param desc
	 */
	public Item(String name, String desc)
	{
		itemName = name;
		itemDescription = desc;
		floorDescription = "There is a " + name + " on the ground.";
	}
}
