package com.lovecraft;
/**
 * 
 * @author Michael
 * @comments Creates an item for future use by the user or the room.
 */

public class Item extends GameObject {

	String floorDescription ;
	/**
	 * Saves the item, and gives it a physical description. 
	 * @param name
	 * @param desc
	 */
	public Item(String name, String desc)
	{
		objectName = name;
		description = desc;
		floorDescription = "There is a " + name + " on the ground.";
	}
}
