package com.lovecraft;
import java.util.Vector;

public abstract class GameObject {
	static Inventory playerInventory = new Inventory();
	
	static String[][] Items = new String[5][6];
	static int count = 0;
	
	protected static String description = "";
	protected static String objectName = "";

//	public String getDescription(String name)
//	{
//		System.out.println(description);
//		return description;
//	}

	public static void setDescription(String pName, String pDescription)
	{
		count++;
		objectName = pName;
		description = pDescription;
		Items[count][count] = objectName;
		Items[count][count+1] = description;
	}

	public static String getDescription(String name)
	{
		name = name.toLowerCase();
		if(playerInventory.contains(name))
		{
			for (int i = 1; i <= count; i++)
			{
				if(Items[i][i].equals(name))
				{
					System.out.println(Items[i][i+1]);
				}
				else
					System.out.println("Breakpoint " + i);
			}
		}
		else
			System.out.println("I apologize, but you can only look at items in your inventory.");
		return name;
	}
	
	public static void inventoryContents() 
	{
//		getName();
		System.out.println("Contents of " + objectName + ":");
		for (int i = 0; i < playerInventory.size(); i++) {
			System.out.println("	" + "-" + playerInventory.get(i));
		}
	}

	
//	 can the object be used with the given parameters?
//	 i.e. can torch be used with fire to create light?
	 
	public abstract boolean useObject();
	

}
