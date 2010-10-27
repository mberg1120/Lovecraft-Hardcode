package com.lovecraft;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * @author Michael
 * @author Steven
 * @author Nathan
 * @version 1.17
 * @comments Totally finished, the only 'edits' that should be done are to the XML documents to make
 * the story seem more relevant.
 * @ToDo Everything works, just change descriptions for keys and when the user 'talks to him/herself'.
 *
 *
 */

public class Client
{
	static Room currentRoom;
	static boolean fuel = false;
	static boolean lamp = false;
	static boolean match = false;
	static Player ourPlayer = new Player();
	static 	Scanner scan = new Scanner(System.in);
	public static void main(String[] args)
	{
		//Creates a new XMLReader that will be used once, as all the info
		//will be parsed.
		XMLReader reader = new XMLReader();
		
		// Input variable.
		String userInput;
		
		// Creates a boolean to be use with the DoWhile loop later
		boolean keepGoing = true;
		
		//Sets the first room to the first room from the XML parser.
		currentRoom = reader.XML();
		
		// Creates a tokenizer to cut up the input from the user.
		StringTokenizer tokenz;
		
		// DoWhile loop that will continue looping until the user reaches the end, and then it will
		// terminate and continue forward. Most of the loop is self explanatory, will not need to comment
		// much on it. 
		System.out.println(currentRoom.toString());
		do
		{
			if(lamp == true) // this still needs to be fixed, otherwise, it is pretty much done. I will fix this tomorrow, when I'm less tired.
				currentRoom.isFirst = false;
			else
				currentRoom.isFirst = true;	
			if(match == true && fuel == true && ourPlayer.playerInventory.contains("lamp"))
				lamp = true;
			userInput = scan.nextLine();
			userInput.toLowerCase();
			tokenz = new StringTokenizer(userInput);
			while(tokenz.countTokens()==0)
			{
				System.out.println("Bad player! Enter a command!");
				printMenu();
				userInput = scan.nextLine();
				userInput.toLowerCase();
				tokenz = new StringTokenizer(userInput);
			}
			// these have been converted to lower case, so equalsIgnoreCase is unnecessary
			String command = tokenz.nextToken();
			String operand = "";
			if(tokenz.hasMoreTokens())
				operand = tokenz.nextToken();
			while(tokenz.hasMoreTokens()) //Skeleton key, or if the user wants to add more words for some reason.
				operand += " " + tokenz.nextToken();		
			if(command.equals("go"))
			{
				if(operand.equals("north")||operand.equals("n"))
				{
					move(currentRoom.north, "North");
				}	
				else if(operand.equals("east")||operand.equals("e"))
				{
					move(currentRoom.east, "East");
				}	
				else if(operand.equals("south")||operand.equals("s"))
				{
					move(currentRoom.south, "South");
				}
				else if(operand.equals("west")||operand.equals("w"))
				{
					move(currentRoom.west, "West");
				}
				else
					System.out.println("You either didn't specify, or didn't spell the direct correctly! Please say 'go' and then a direction after that!");
			}
			else if(command.equals("take"))
			{
				if(currentRoom.roomInventory.contains(operand))
				{
					ourPlayer.playerInventory.add(currentRoom.roomInventory.getItemFromName(operand));
					currentRoom.roomInventory.remove(operand);
					System.out.println("You have picked up the " + operand + ".");
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
					System.out.println("You have dropped the " + operand + " on the ground.");
					if(operand.equals("lamp"))
						lamp = false;
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
					System.out.println("You may only look at items in your inventory. Or the room.");
			}
			else if (command.equals("use"))
			{
				use(operand);
			}		
			//This will open up the user's inventory if they type in the correctt command.
			else if (command.equals("i") || command.equals("inventory"))
			{
				ourPlayer.playerInventory.inventoryContents();
			}
			else if (command.equals("command") || command.equals("commands") || command.equals("c"))
			{
				printMenu();
			}
			//If they don't type anything out, it will print out the full command list.
			else
			{
				System.out.println("Please read the command list");
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
				"and put him in the cave.\n\n Do you wish to make your escape?");
		// Allows the user to read the above message, and will print out the end credits once they escape.
		do
		{
			userInput = scan.nextLine();
			userInput.toLowerCase();
			keepGoing = true;
			if(userInput.equals("yes") || userInput.equals("y") || userInput.equals("escape"))
			{
				System.out.println("Congratulations, You beat Project LoveCraft!\n" +
						"Designed, Written, and Coded By:\n" +
						"Steven Honda\n" +
						"Michael Berg\n" +
						"Nathan Secrist");
				keepGoing = false;
			}
			else
				System.out.println("To escape, type 'yes' 'y' or 'escape'.");
			}while(keepGoing == true);
		}
	/**
	 * Prints the command menu to the user.
	 */
	public static void printMenu()
	{
		System.out.println("\nSupported commands:" +
		"\n-go north/east/south/west: travel one room in the specified direction, watch for holes!" +
		"\n-take * : pick-up an item" +
		"\n-i or inventory: shows the items in your inventory" +
		"\n-drop * : drop an item" +
		"\n-use * : use an item");
	}
	/**
	 * Allows the user to move, and will print out
	 * if the user can't go that way.
	 * @param direction
	 * @param name
	 */
	public static void move(Room direction, String name)
	{
		if(direction.Name.equals("Chasm"))
		{
			Chasm.fallIntoChasm(scan);
		}
		else if(direction.Name.equals("Final") && ourPlayer.playerInventory.contains("skeleton key"))
		{
			currentRoom = direction;
		}
		else if(direction.Name.equals("Final") && ourPlayer.playerInventory.contains("skeleton key") == false)
			System.out.println("You do not have a Skeleton Key in your inventory.");
		else if (direction == null || direction == currentRoom)
		{
			System.out.println("You can't go " + name + "!");
		}
		else if(direction.isLit == false && lamp == true)
		{
			direction.isLit = true;		
			currentRoom = direction;
			System.out.println(currentRoom.toString());
			currentRoom.roomInventory.inventoryDescriptions();
			return;
		}
		else if(direction.isLit == true && lamp == false && direction.Name.equals("A1")	||
				direction.isLit == true && lamp == false && direction.Name.equals("A2") ||
				direction.isLit == true && lamp == false && direction.Name.equals("B1") ||
				direction.isLit == true && lamp == false && direction.Name.equals("B2") ||
				direction.isLit == true && lamp == false && direction.roomInventory.contains("lamp"))
		{
			currentRoom = direction;
			System.out.println(currentRoom.toString());
			currentRoom.roomInventory.inventoryDescriptions();
			return;
		}
		else if (direction.isLit == true && lamp == false)
			direction.isLit = false;
		else if(direction.isLit)
		{
			if(direction.isLocked && ourPlayer.playerInventory.contains("key") == false)
				System.out.println("I'm sorry, that room is locked and you can't go that way.");
			else if(direction.isLocked && ourPlayer.playerInventory.contains("key"))
			{
				direction.isLocked = false;
				currentRoom = direction;
				ourPlayer.playerInventory.remove("key");
				System.out.println(currentRoom.toString());
				currentRoom.roomInventory.inventoryDescriptions();
			}
			else
			{
				if(direction == null || direction.equals(currentRoom))
					System.out.println("You can't go " + name + "!");
				else
				{
					currentRoom = direction;
					System.out.println(currentRoom.toString());
					currentRoom.roomInventory.inventoryDescriptions();
				}
			}
		}
		else
			System.out.println("You can't see where you're going, you may not proceed.");
	}
	/**
	 * Method that allows the user to use different items.
	 * @param operand
	 */
	public static void use(String operand)
	{
		// This will use a match if the player has a match, and has fuel used.
		if(operand.equals("match") && ourPlayer.playerInventory.contains(operand) && fuel == true)
		{
			lamp = true;
			match = true;
			System.out.println("You have used the match on the fuel." +
					"\nYou can now see where you're going.");
			ourPlayer.playerInventory.remove(operand);
		}
		//If the player tries to use the match and doesn't have fuel, it will tell the user that fuel
		//needs to be in the lamp to use the match.
		else if(operand.equals("match") && ourPlayer.playerInventory.contains(operand) && fuel == false)
		{
			System.out.println("I'm sorry, but you haven't put any fuel in the lamp, so there is nothing for you to light.");
		}
		//This will check if the player uses the word 'lamp' after use, and will check
		//the player's inventory for all the necessary components to use the lamp
		else if (operand.equals("lamp") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("match") && ourPlayer.playerInventory.contains("fuel")
				|| operand.equals("lamp") && fuel == true && ourPlayer.playerInventory.contains("match"))
		{
			System.out.println("You have put the fuel in the lamp, and used your match to light the lamp." +
					"\nYou can now see where you're going.");
			ourPlayer.playerInventory.remove("fuel");
			ourPlayer.playerInventory.remove("match");
			lamp = true;
			match = true;
			fuel = true;
		}
		//And if none of the necessary components are in the user's inventory, it will say you can't use the lamp.
		else if (operand.equals("lamp") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("match") == false && ourPlayer.playerInventory.contains("fuel") 
				|| operand.equals("lamp") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("match") && ourPlayer.playerInventory.contains("fuel") == false
				|| operand.equals("lamp") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("match") == false && ourPlayer.playerInventory.contains("fuel") == false)
		{
			System.out.println("You can't use your lamp without a match and fuel!");
		}
		//This will put the fuel in the lamp if the user's inventory contains a lamp.
		else if(operand.equals("fuel") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("lamp"))
		{
			fuel = true;
			System.out.println("You have put the fuel in the lamp.");
			ourPlayer.playerInventory.remove(operand);
		}
		//If the user's inventory does not contain a lamp, it has no where to put the fuel in!
		else if(operand.equals("fuel") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("lamp") == false)
		{
			System.out.println("You don't have a lamp to put your fuel in!");
		}
		//These next statements check if there is a locked door anywhere, and if they have a key in their inventory, 
		//it will unlock the room and move them into the room
		else if(operand.equals("key") && currentRoom.south.isLocked && ourPlayer.playerInventory.contains(operand))
		{
			currentRoom.south.isLocked = false;
			ourPlayer.playerInventory.remove(operand);
			currentRoom = currentRoom.south;
			System.out.println(currentRoom.toString());
			currentRoom.roomInventory.inventoryDescriptions();
			
		}
		else if(operand.equals("key") && currentRoom.north.isLocked && ourPlayer.playerInventory.contains(operand))
		{
			currentRoom.north.isLocked = false;
			currentRoom = currentRoom.north;
			ourPlayer.playerInventory.remove(operand);
			System.out.println(currentRoom.toString());
			currentRoom.roomInventory.inventoryDescriptions();
		}	
		//This statement reads in "skeleton key" from the user and opens the final room, if they 
		//have a skeleton key in their inventory.
		else if(operand.equals("skeleton key") && currentRoom.west.equals("Final") && ourPlayer.playerInventory.contains("skeleton key"))
			currentRoom = currentRoom.west;
		//This will tell them they can't go to the final room because they don't have a skeleton key.
		else if(operand.equals("skeleton key") && currentRoom.west.equals("Final") && ourPlayer.playerInventory.contains("skeleton key") == false)
			System.out.println("You don't have a skeleton key!");
		else
			System.out.println("You either can't use that item, or do not have that item, or misspelled the item name.");
	}
}
