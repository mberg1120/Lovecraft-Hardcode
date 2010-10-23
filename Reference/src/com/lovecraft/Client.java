package com.lovecraft;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * @author Michael
 * @author Steven
 * @author Nathan
 * @author Kellie
 * @version 1.16
 * @comments Basically finished. Originally used as reference, have made a ton of edits since original assistance from friend.
 *	He mainly assisted with reorganizing how code was written, and helped with the XML. I have since editing both XML documents, 
 *	tweaked the XMLReader a lot, and added different functionality to Inventory.java, Item.java, and Client.java
 * @ToDo Use command finished (shouldn't be difficult) - Tweak the Skeleton Key... Don't know why it keeps breaking =-\
 *
 *
 */

public class Client
{
	public static void main(String[] args)
	{
		XMLReader reader = new XMLReader();
		// Input variable.
		String userInput;
		/*
		 * Below lines were used in previous version, and will be changed via a separate XML file.
		 * 
		String key = "There is a Key on the ground... It may be useful in the future";
		String skeletonKey = "This is a Skeleton Key... It will be useful in the future.";
		String lamp = "This lamp, when used with fuel and a match will light up the Cave.";
		String match = "This match, when used with fuel and a lamp, will light up the Cave.";
		String keyDesc = "As you look at the key, you can tell it is rather shiny.";
		String skeletonKeyDesc = "This key is more unique than the other one. It has a skeleton head on it, and it is clear it will be useful.";
		String matchDesc = "Perhaps you should combine this with a lamp and some fuel?";
		String lampDesc = "If you add fuel and a match, this will probably light up the Cave.";
		*/
		// Creates a boolean to be use with the DoWhile loop later
		boolean keepGoing = true;
		Player ourPlayer = new Player();
		/*
		 * Below lines were used in previous version and are no longer needed
		 * 
		//Sets up an array of the Room object, and will allow us
		//to go from north south east west and set up specific items 
		//for specific rooms (also allowing drop and take from room). 
//		ArrayList<Room> Rooms = new ArrayList<Room>();
//		Room A1 = new Room("Start"); //NW
//		Room B1 = new Room("B1"); //SW
//		Room A2 = new Room("A2"); //SE
//		Room B2 = new Room("B2"); //NE
//		Rooms.add(A1);
//		Rooms.add(A2);
//		Rooms.add(B2);
//		Rooms.add(B1);
//		A1.east = A2;
//		A1.south = B1;
//		A2.west = A1;
//		A2.south = B2;
//		B1.north = A1;
//		B1.east = B2;
//		B2.north=A2;
//		B2.west = B1;	*/	
		
		Room currentRoom = reader.XML();
		
		// Prints the introduction to the user.
		System.out.println("Welcome user, this is Project Love Craft... You have found yourself lying on the ground," +
				"\nand it  appears you have a bump on the back of your head. As you look around, you only " +
				"\nhave 3 areas that are visible, and it appears you are stuck in a dungeon or cave of some sort. " +
				"\nOnce you go forth into this dungeon, you will find objects, it is your choice how to handle them. " +
				"\nYou will have the ability to receive hints in each area, and your first hint is that you must type" +
				"\n'go' and then the word North, West, East or South to move. Currently, you may only go east or south.");
		Scanner scan = new Scanner(System.in);
		StringTokenizer tokenz;
		// DoWhile loop that will continue looping until the user reaches the end, and then it will
		// terminate and continue forward. Most of the loop is self explanatory, will not need to comment
		// much on it. 
		do{
		userInput = scan.nextLine();
		userInput.toLowerCase();
		tokenz = new StringTokenizer(userInput);
		if(tokenz.countTokens()==0)
		{
			System.out.println("bad player! enter a command!");
			printMenu();
		}
		// these have been converted to lower case, so equalsIgnoreCase is unnecessary
		String command = tokenz.nextToken();
		String operand = "nope";
		if(tokenz.hasMoreTokens())
			operand = tokenz.nextToken();
		command.toLowerCase();
		operand.toLowerCase();
		
		if(command.equals("go"))
		{
			if(operand.equals("north")||operand.equals("n"))
			{
				if(currentRoom.north == null || currentRoom.north == currentRoom)
					System.out.println("you can't go north!");
				else
				{
					currentRoom = currentRoom.north;
					currentRoom.roomInventory.inventoryDescriptions();
				}
			}
			if(operand.equals("east")||operand.equals("e"))
			{
				if(currentRoom.east == null || currentRoom.east == currentRoom)
					System.out.println("you can't go east!");
				else
				{
					currentRoom = currentRoom.east;
					currentRoom.roomInventory.inventoryDescriptions();
				}
			}
			if(operand.equals("south")||operand.equals("s"))
			{
				if(currentRoom.south == null || currentRoom.south == currentRoom)
					System.out.println("you can't go south!");
				else
				{
					currentRoom = currentRoom.south;
					currentRoom.roomInventory.inventoryDescriptions();
				}
			}
			if(operand.equals("west")||operand.equals("w"))
			{
				if(currentRoom.west == null || currentRoom.west == currentRoom)
					System.out.println("you can't go west!");
				else
				{
					currentRoom = currentRoom.west;
					currentRoom.roomInventory.inventoryDescriptions();
				}
			}
		}
		else if(command.equals("take"))
		{
			if(currentRoom.roomInventory.contains(operand))
			{
				ourPlayer.playerInventory.add(currentRoom.roomInventory.getItemFromName(operand));
				currentRoom.roomInventory.remove(operand);
			}
			// skeleton key
			else if(tokenz.hasMoreTokens() && currentRoom.roomInventory.contains(operand +" " + tokenz.nextToken()))
			{
				ourPlayer.playerInventory.add(currentRoom.roomInventory.getItemFromName(operand));
			}
			else
				System.out.println("That item doesn't exist in this room !");
		}
		else if(command.equals("drop"))
		{
			if(ourPlayer.playerInventory.contains(operand))
			{
				currentRoom.roomInventory.add(ourPlayer.playerInventory.getItemFromName(operand));
				ourPlayer.playerInventory.remove((operand));
			}
			// skeleton key
			else if(tokenz.hasMoreTokens() && ourPlayer.playerInventory.contains(operand +" " + tokenz.nextToken()))
			{
				currentRoom.roomInventory.add(ourPlayer.playerInventory.getItemFromName(operand));
			}
			else
				System.out.println("You don't have that item !");
		}
		else if(command.equals("look"))
		{
			if(operand.equals("room"))
				System.out.println(currentRoom.getDescription());
			else if (ourPlayer.playerInventory.contains(operand))
				System.out.println(ourPlayer.playerInventory.getItemFromName(operand).itemDescription);
			else
				System.out.println("You may only look at items in your inventory.");
		}
		else if (command.equals("use"))
		{
			// lamp/match/fuel
		}
	
		else if (command.equals("i") || command.equals("inventory"))
		{
			ourPlayer.playerInventory.inventoryContents();
		}
		else
		{
			System.out.println("geez dude, read the command list");
			printMenu();
		}
		if(currentRoom.west == currentRoom && currentRoom.north == currentRoom && currentRoom.south == currentRoom)
			keepGoing = false;
		}while(keepGoing == true);
		// Prints the end dialogue
		System.out.println("As you open the door... It seems to be that you're at the beginning of a Cave looking out. There also\n" +
				"seems to be a park, how convenient. You don't recognize any of your surroundings. Hell, you can't\n" +
				"remember anything about yourself, let alone what you are, so there is no reason for any of your\n" +
				"surroundings to be known. Though, you see someone coming. If you are to make your escape, you'll\n" +
				"need to take out any eye witnesses. You bludgeon him in the back of the head with your wooden object\n" +
				"and put him in the cave. Do you wish to make your escape?");
		// Allows the user to read the above message, and will print out the end credits once they escape.
		do{
		userInput = scan.nextLine();
		userInput.toLowerCase();
		keepGoing = true;
		if(userInput.equals("yes") || userInput.equals("y") || userInput.equals("escape"))
		{
			System.out.println("Congratulations, You beat Project LoveCraft!\n" +
				"Designed, Written, and Coded By:\n" +
				"Stephen Honda\n" +
				"Michael Berg\n" +
				"Nathan Secrist\n" +
				"Kellie Watson");
			keepGoing = false;
		}
		else
			System.out.println("To escape, type 'yes' 'y' or 'escape'.");
		}while(keepGoing == true);
	}
	public static void printMenu()
	{
		System.out.println("\nSupported commands:");
		System.out.println("-go north/east/south/west: travel one room in the specified direction, watch for holes!");
		System.out.println("-take * : pick-up an item");
		System.out.println("-drop * : drop an item");
		System.out.println("-use * : use an item");
	}
}