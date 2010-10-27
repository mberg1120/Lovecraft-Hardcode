package com.lovecraft;

import java.util.Vector;
/**
 * 
 * @author Michael
 * @comments This is the Inventory class that is used for each Room and Player.
 */

public class Inventory {
	
	Vector<Item> inventory = new Vector<Item>();
	/**
	 * Adds an item to the inventory.
	 * @param toAdd
	 */
	public void add(Item toAdd)
	{
		inventory.add(toAdd);
	}
	/**
	 * Removes an item from the inventory.
	 * @param objectName
	 */
	public void remove(String objectName)
	{
		inventory.remove(getItemFromName(objectName));
	}
	/**
	 * Checks the inventory if it physically has that item.
	 * @param objectName
	 * @return
	 */
	public boolean contains(String objectName)
	{
		for(int i=0; i<inventory.size(); i++)
		{
			if(inventory.get(i).itemName.equals(objectName))
				return true; // found an item matching that description!
		}
		return false; //we don't have an item by that name
	} 
	/**
	 * Returns the size of the inventory.
	 * @return
	 */
	public int size()
	{
		return inventory.size();
	}
	/**
	 * Shows the contents of the player's inventory.
	 */
	public void inventoryContents() 
	{
		if (inventory.size() == 0)
			System.out.println("You don't have any items in your inventory!");
		else
			System.out.println("Contents of Inventory: ");
		for (int i = 0; i < inventory.size(); i++) 
		{
			System.out.println("-" + inventory.get(i).itemName);
		}
	}
	/**
	 * Prints out the description of the items
	 * that are in the room.
	 */
	public void inventoryDescriptions()
	{
		for (int i = 0; i < inventory.size(); i++) 
		{
			System.out.println(inventory.get(i).floorDescription);
		}
	}
	/**
	 * Finds the item name in the inventory. 
	 * @param objectName
	 * @return
	 */
	public Item getItemFromName(String objectName)
	{
		for(int i = 0; i < inventory.size(); i++)
		{
			if(inventory.get(i).itemName.equals(objectName))
				return inventory.get(i);
		}
		System.out.println("Inventory error! We were looking for " + objectName + " but couldn't find it");
		return null;
	}

}
