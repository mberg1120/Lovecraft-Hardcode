package com.lovecraft;

import java.util.Vector;

public class Inventory {
	
	Vector<Item> inventory = new Vector<Item>();
	
	public void add(Item toAdd)
	{
		inventory.add(toAdd);
	}
	public void remove(String objectName)
	{
		inventory.remove(getItemFromName(objectName));
	}
	public boolean contains(String objectName)
	{
		for(int i=0; i<inventory.size(); i++)
		{
			if(inventory.get(i).itemName.equals(objectName))
				return true; // found an item matching that description!
		}
		return false; //we don't have an item by that name
	} 
	public int size()
	{
		return inventory.size();
	}
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
	public void inventoryDescriptions()
	{
		for (int i = 0; i < inventory.size(); i++) 
		{
			System.out.println(inventory.get(i).floorDescription);
		}
	}
	public Item getItemFromName(String objectName)
	{
		for(int i = 0; i < inventory.size(); i++)
		{
			if(inventory.get(i).itemName.equals(objectName))
				return inventory.get(i);
		}
		System.out.println("Inventory error! we were looking for " + objectName + " but couldn't find it");
		return null;
	}

}
