package com.lovecraft;

import java.util.Vector;

public class Inventory {
	
	Vector inventory = new Vector();
	
	public void add(String objectName) {
		inventory.add(objectName);
		return;
	}
	public void remove(String objectName)
	{
		inventory.remove(objectName);
		return;
	}
	public boolean contains(String objectName)
	{
		boolean item;
		if(inventory.contains(objectName))
			item = true;
		else
			item = false;
		return item;
	}
	public int size()
	{
		return inventory.size();
	}
	public Object get(int i)
	{
		return inventory.get(i);
	}
	public void inventoryContents() {
		System.out.println("Contents of Inventory: ");
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println("	" + "-" + inventory.get(i));
		}
	}
	public void room()
	{
		for (int i = 0; i < inventory.size(); i++)
		{
			System.out.println(inventory.get(i));
		}
	}
	


}
