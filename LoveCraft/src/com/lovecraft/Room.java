package com.lovecraft;


public class Room extends GameObject {
	Room north,east,south,west; // these are the rooms N/E/S/W of me
	String Name; // this is my name
	String holder; // this is a holder string for while the object is being constructed
	Inventory roomInventory; // this is a list of items that i have
	boolean isLocked = false;
	boolean isLit = true;
	
	public Room(String name)
	{
		Name = name;
		roomInventory = new Inventory(); // i need an empty inventory to add things to
	}
	public Room(String name,String hold)
	{
		Name = name;
		holder = hold;
		roomInventory = new Inventory(); // i need an empty inventory to add things to
	}

	public void receiveItem(Item name)
	{
		roomInventory.add(name); // the checks occur in client, if we make it here just add the item
	}
	public void roomContents() 
	{
		roomInventory.inventoryContents();
	}
	public void removeItem(String item)
	{
		roomInventory.remove(item);
	}

}
