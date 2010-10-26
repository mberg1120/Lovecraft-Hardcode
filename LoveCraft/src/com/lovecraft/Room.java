package com.lovecraft;

/**
 * Allows creation of a room with several different variables that can be used later.
 * @author Michael
 *
 */

public class Room extends GameObject {
	Room north,east,south,west; // these are the rooms N/E/S/W of me
	String Name; // this is my name
	String holder; // this is a holder string for while the object is being constructed
	Inventory roomInventory; // this is a list of items that i have
	boolean isLocked = false;
	boolean isLit = true;
	/**
	 * Calls/creates a new room with the parameter 'name'. 
	 */
	public Room(String name)
	{
		Name = name;
		roomInventory = new Inventory(); // i need an empty inventory to add things to
	}
	/**
	 * Creates or calls a new room with the parameters 'name' and 'hold'. 
	 * @param name
	 * @param hold
	 */
	public Room(String name,String hold)
	{
		Name = name;
		holder = hold;
		roomInventory = new Inventory(); // i need an empty inventory to add things to
	}
	/**
	 * Adds an item to the room inventory. 
	 * @param name
	 */
	public void receiveItem(Item name)
	{
		roomInventory.add(name); // the checks occur in client, if we make it here just add the item
	}
	/**
	 * Prints out the description of the room inventory. 
	 */
	public void roomContents()
	{
		roomInventory.inventoryContents();
	}
	/**
	 * Removes an item from the inventory. 
	 * @param item
	 */
	public void removeItem(String item)
	{
		roomInventory.remove(item);
	}

}
