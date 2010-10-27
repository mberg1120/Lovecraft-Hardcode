package com.lovecraft;

/**
 * Allows creation of a room with several different variables that can be used later.
 * @author Michael
 *
 */

public class Room extends GameObject {
	Room north,east,south,west; // these are the rooms N/E/S/W of me
	String holder; // this is a holder string for while the object is being constructed
	boolean isLocked = false;
	boolean isLit = true;
	boolean isFirst = true;
	Inventory roomInventory;
	/**
	 * Calls/creates a new room with the parameter 'name'. 
	 */
//	public Room(String name)
//	{
//		Name = name;
//		roomInventory = new roomInventory(); // i need an empty roomInventory to add things to
//	}
	/**
	 * Creates or calls a new room with the parameters 'name' and 'hold'. 
	 * @param name
	 * @param hold
	 */
	public Room(String name,String hold)
	{
		objectName = name;
		holder = hold;
		roomInventory = new Inventory();
	}
	/**
	 * Adds an item to the room roomInventory. 
	 * @param name
	 */
	public void receiveItem(Item name)
	{
		roomInventory.add(name); // the checks occur in client, if we make it here just add the item
	}
	/**
	 * Prints out the description of the room roomInventory. 
	 */
	public void roomContents()
	{
		roomInventory.inventoryContents();
	}
	/**
	 * Removes an item from the roomInventory. 
	 * @param item
	 */
	public void removeItem(String item)
	{
		roomInventory.remove(item);
	}
	@Override
	public String toString()
	{
		if(description2 == null || description2.equals(""))
			description2 = description;
		if(isFirst == true)
			return description;
		else
			return description2;
	}

}
