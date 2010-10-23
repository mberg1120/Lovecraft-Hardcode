package com.lovecraft;


public class Item {

	String itemName;
	String itemDescription;
	String holder;
	String floorDescription;
	
	public Item(String name, String desc, String floor)
	{
		itemName = name;
		itemDescription = desc;
		floorDescription = floor;
	}
}
