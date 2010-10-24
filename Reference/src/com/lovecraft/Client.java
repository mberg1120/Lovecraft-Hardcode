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
	static Room currentRoom;
	static boolean fuel = false;
	static boolean lamp = false;
	static Player ourPlayer = new Player();
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
		
		currentRoom = reader.XML();
		
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
			userInput = scan.nextLine();
			userInput.toLowerCase();
			tokenz = new StringTokenizer(userInput);
		}
		// these have been converted to lower case, so equalsIgnoreCase is unnecessary
		String command = tokenz.nextToken();
		String operand = "nope";
		if(tokenz.hasMoreTokens())
			operand = tokenz.nextToken();
		while(tokenz.hasMoreTokens()) //Skeleton key, or if the user wants to add more words for some reason.
			operand += " " + tokenz.nextToken();
		command.toLowerCase();
		operand.toLowerCase();
		
		if(command.equals("go"))
		{
			if(operand.equals("north")||operand.equals("n"))
			{
				move(currentRoom.north, "North");
			}
			if(operand.equals("east")||operand.equals("e"))
			{
				move(currentRoom.east, "East");
			}
			if(operand.equals("south")||operand.equals("s"))
			{
				move(currentRoom.south, "South");
			}
			if(operand.equals("west")||operand.equals("w"))
			{
				move(currentRoom.west, "West");
			}
		}
		else if(command.equals("take"))
		{
			if(currentRoom.roomInventory.contains(operand))
			{
				ourPlayer.playerInventory.add(currentRoom.roomInventory.getItemFromName(operand));
				currentRoom.roomInventory.remove(operand);
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
			else
				System.out.println("You don't have that item !");
		}
		else if(command.equals("look"))
		{
			if(operand.equals("room"))
				System.out.println(currentRoom.description);
			else if (ourPlayer.playerInventory.contains(operand))
				System.out.println(ourPlayer.playerInventory.getItemFromName(operand).itemDescription);
			else
				System.out.println("You may only look at items in your inventory.");
		}
		else if (command.equals("use"))
		{
			// lamp/match/fuel
			if(operand.equals("match") && ourPlayer.playerInventory.contains(operand) && fuel == true)
				{
				lamp = true;
				System.out.println("You have used the match on the fuel.");
				ourPlayer.playerInventory.remove(operand);
				}
			else if(operand.equals("fuel") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("lamp"))
			{
				fuel = true;
				System.out.println("You have put the fuel in the lamp.");
				ourPlayer.playerInventory.remove(operand);
			}
			else if(operand.equals("key") && currentRoom.south.isLocked)
			{
				currentRoom.south.isLocked = false;
				currentRoom = currentRoom.south;
				System.out.println(currentRoom.description);
				currentRoom.roomInventory.inventoryDescriptions();
				
			}
			else if(operand.equals("key") && currentRoom.west.isLocked)
			{
				currentRoom.west.isLocked = false;
				currentRoom = currentRoom.south;
				System.out.println(currentRoom.description);
				currentRoom.roomInventory.inventoryDescriptions();
			}
			else if(operand.equals("key") && currentRoom.east.isLocked)
			{
				currentRoom.east.isLocked = false;
				currentRoom = currentRoom.south;
				System.out.println(currentRoom.description);
				currentRoom.roomInventory.inventoryDescriptions();
			}
			else if(operand.equals("key") && currentRoom.north.isLocked)
			{
				currentRoom.north.isLocked = false;
				currentRoom = currentRoom.south;
				System.out.println(currentRoom.description);
				currentRoom.roomInventory.inventoryDescriptions();
			}	
		}
	
		else if (command.equals("i") || command.equals("inventory"))
		{
			ourPlayer.playerInventory.inventoryContents();
		}
		else
		{
			System.out.println("Read the command list");
			printMenu();
		}
		if(currentRoom.Name.equals("Final"))
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
	public static void move(Room direction, String name)
	{
		if(direction.Name.equals("Final") && ourPlayer.playerInventory.contains("skeleton key"))
		{
			currentRoom = direction;
		}
		else if(direction.Name.equals("Final Room") && ourPlayer.playerInventory.contains("skeleton key") == false)
			System.out.println("You do not have a Skeleton Key in your inventory.");
		else if(direction.isLit)
		{
			if(direction.isLocked)
				System.out.println("I'm sorry, that room is locked and you can't go that way.");
			else
			{
				if(direction == null || direction.equals(currentRoom))
					System.out.println("You can't go " + name + "!");
				else
				{
					currentRoom = direction;
					System.out.println(currentRoom.description);
					currentRoom.roomInventory.inventoryDescriptions();
				}
			}
		}
		else if(direction.isLit == false && lamp == true)
		{
			direction.isLit = true;		
			currentRoom = direction;
			System.out.println(currentRoom.description);
			currentRoom.roomInventory.inventoryDescriptions();
		}
		else
			System.out.println("You can't see  where you'e going, you may not proceed.");
	}
}
