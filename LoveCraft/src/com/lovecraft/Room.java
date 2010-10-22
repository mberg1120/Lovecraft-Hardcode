package com.lovecraft;

import java.util.Vector;
import java.util.Random;
import java.util.Scanner;

public class Room extends GameObject {

	String Name;
	String Key = "hi2u";
	public Room(String name)
	{
		Name = name;
	}

	@Override
	public boolean useObject() {
		
		// use object will reflect what happens when you move from room
		// to room or drop an object... or even use an object with another
		// xml should be able to hold a combination of objects and what happens
		// when the objects are used with each other.
		
		return false;
	}
	public static void dropItem(String name)
	{
		if(playerInventory.contains(name))
		{
			roomInventory.add(name);
			playerInventory.remove(name);
			System.out.println("You have removed the item from your inventory, and it is now lying on the ground.");
		}
		else
			System.out.println("You attempted to remove an Item you do not have.");
		return;
	}
	public void roomContents() {
		getName();
		System.out.println("Contents of " + objectName + ":");
		for (int i = 0; i < roomInventory.size(); i++) {
			System.out.println("	" + "-" + roomInventory.get(i));
		}
	}
	public void itemsInRoom()
	{
		roomInventory.room();
	}
	public static void addItem(String item)
	{
		if(roomInventory.contains(item))
		{
			roomInventory.remove(item);
			playerInventory.add(item);
			System.out.println("The item is now in your inventory.");
		}
		else
			System.out.print("You have attempted to pick up an item that is not in this room.");
		
		return;
	}
	public static void initialItem(String name)
	{
		roomInventory.add(name);
	}
//	public static void moveTo(int x, int y)
//	{																			
//		System.out.println(move[x][y]);
//	}
	public static void Chasm(Scanner scan)
	{
		boolean keepGoing = true;
		Random generator = new Random();
		String userInput;
		System.out.println("You jumped into the Chasm....\n" +
				"You have found yourself completely surrounded by darkness. There is no clear path," +
				"\nand there is no light source. Your torch was dropped on the ground before you fell. " +
				"\nYou may go North, South, East, or West, but I doubt it will matter.");
		do{
			int r = generator.nextInt();
			userInput = scan.nextLine();
			r = r % 2;
			if(r == 0)
			{
				System.out.println("You have managed to climb out of the chasm and returned to the Cave." +
						"\nYou have returned to the room you were previously in." +
						"\nI'd suggest not going back into the Chasm, that proved... unhelpful.");
				keepGoing = false;
			}
			else
				System.out.println("As you continue onward, darkness still surrounds you. It seems no progress has been made. " +
						"\nYou may go North, South, East or West.");
		}while(keepGoing == true);
	}
	@Override
	public String toString()
	{
		return Name ;
	}

}
