package com.lovecraft;


public class Item {

	String itemName;
	String itemDescription;
	String holder;
	String floorDescription ;
	
	public Item(String name, String desc)
	{
		itemName = name;
		itemDescription = desc;
		floorDescription = "There is a " + name + " on the ground.";
	}
}
