package com.lovecraft;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * @author Michael
 * @author Steven
 * @author Nathan
 * @version 1.20
 * @comments Moved mainly everything to XML.
 * @ToDo Now just to spoof things up basically.
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
			userInput = userInput.toLowerCase();
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
					ourPlayer.playerInventory.remove(operand);
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
					System.out.println(ourPlayer.playerInventory.getItemFromName(operand).description);
				else
					System.out.println("You may only look at items in your Inventory. Or the room.");
			}
			else if (command.equals("use"))
			{
				use(operand);
			}		
			//This will open up the user's Inventory if they type in the correct command.
			else if (command.equals("i") || command.equals("inventory"))
			{
				ourPlayer.playerInventory.inventoryContents();
			}
			//This will print out the command list / command menu
			else if (command.equals("command") || command.equals("commands") || command.equals("c") || command.equals("menu"))
			{
				printMenu();
			}
			//Will allow the user to escape if they're in the final room, otherwise they return to the cave.
			else if(currentRoom.objectName.equals("Final") && command.equals("y") ||
					currentRoom.objectName.equals("Final") && command.equals("yes"))
			{
				keepGoing = false;
				currentRoom = currentRoom.west;
			}
			else if(currentRoom.objectName.equals("Final") && !command.equals("y") ||
					currentRoom.objectName.equals("Final") && !command.equals("yes"))
			{
				currentRoom = currentRoom.east;
				System.out.println("You have returned back to the cave in the room you were previously in." +
						"\nIf you wish to make your escape, return back to this room.");
			}
			else if(command.equals("up") && operand.equals("up down down left right left right b a start"))
			{
				System.out.println("Preston Lee has appeared out of no where... He has shining armor that glows as if he is the sun itself." +
						"\nYou pull sunglasses out of your back pocket and put them on. His overwhelming glow is too much for your eyes." +
						"\nHe hands you a paper and says \"Nice work\"" +
						"\nPaper has been added to your inventory.");

				ourPlayer.playerInventory.add(currentRoom.roomInventory.getItemFromName("paper"));
				currentRoom.roomInventory.remove("paper");
			}
				
			//If they don't type anything out, it will print out the full command list.
			else
			{
				System.out.println("Please read the command list");
				printMenu();
			}	

					
			}while(keepGoing == true);
			// This will print out the end credits once the user escapes.
			System.out.println(currentRoom.toString());
		}
	/**
	 * Prints the command menu to the user.
	 */
	public static void printMenu()
	{
		System.out.println("\nSupported commands:" +
		"\n-go north/east/south/west: travel one room in the specified direction, watch for holes!" +
		"\n-take * : pick-up an item" +
		"\n-i or inventory: shows the items in your Inventory" +
		"\n-drop * : drop an item" +
		"\n-use * : use an item");
	}
	/**
	 * Allows the user to move, and will print out
	 * if the user can't go that way.
	 * @param direction
	 * @param objectName
	 */
	public static void move(Room direction, String objectName)
	{
		if(direction.isLit == false && lamp == true)
		{
			direction.isLit = true;
		}		
		else if (direction == null || direction == currentRoom)
		{
			System.out.println("You attempted to go " + objectName + " but you have run into a wall." +
					"\nYou have fallen backwards and hit your head." +
					"\n." +
					"\n." +
					"\n." +
					"\n." +
					"\n." +
					"\n." +
					"\nYou awake several hours later dazed and confused.");
			return;
		}
		else if(direction.isLit == true && lamp == false && direction.objectName.equals("A1")	||
				direction.isLit == true && lamp == false && direction.objectName.equals("A2") ||
				direction.isLit == true && lamp == false && direction.objectName.equals("B1") ||
				direction.isLit == true && lamp == false && direction.objectName.equals("B2") ||
				direction.isLit == true && lamp == false && direction.roomInventory.contains("lamp"))
		{
			currentRoom = direction;
			System.out.println(currentRoom.toString());
			currentRoom.roomInventory.inventoryDescriptions();
			return;
		}
		else if (direction.isLit == true && lamp == false)
			direction.isLit = false;
		if(direction.objectName.equals("Chasm"))
		{
			Chasm.fallIntoChasm(scan);
		}
		else if(direction.objectName.equals("Final") && ourPlayer.playerInventory.contains("skeleton key"))
		{
			currentRoom = direction;
			System.out.println(currentRoom.toString());
			currentRoom.roomInventory.inventoryDescriptions();
		}
		else if(direction.objectName.equals("Final") && ourPlayer.playerInventory.contains("skeleton key") == false)
			System.out.println("You do not have a Skeleton Key in your Inventory.");
		else if(direction.isLit)
		{
			if(direction.isLocked && ourPlayer.playerInventory.contains("key") == false)
				System.out.println("I'm sorry, that room is locked and you can't go that way.");
			else if(direction.isLocked && ourPlayer.playerInventory.contains("key"))
			{
				direction.isLocked = false;
				currentRoom = direction;
				System.out.println("You have unlocked the door moved into the Room.");
				ourPlayer.playerInventory.remove("key");
				System.out.println(currentRoom.toString());
				currentRoom.roomInventory.inventoryDescriptions();
			}
			else
			{
				if(direction == null || direction.equals(currentRoom))
					System.out.println("You can't go " + objectName + "!");
				else
				{
					currentRoom = direction;
					System.out.println(currentRoom.toString());
					currentRoom.roomInventory.inventoryDescriptions();
				}
			}
		}
		else
			System.out.println("You can't see where you're going without a light source, you may not proceed.");
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
		//the player's Inventory for all the necessary components to use the lamp
		else if (operand.equals("lamp") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("match") && ourPlayer.playerInventory.contains("fuel can")
				|| operand.equals("lamp") && fuel == true && ourPlayer.playerInventory.contains("match"))
		{
			System.out.println("You have put the fuel in the lamp, and used your match to light the lamp." +
					"\nYou can now see where you're going.");
			ourPlayer.playerInventory.remove("fuel can");
			ourPlayer.playerInventory.remove("match");
			lamp = true;
			match = true;
			fuel = true;
		}
		//And if none of the necessary components are in the user's Inventory, it will say you can't use the lamp.
		else if (operand.equals("lamp") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("match") == false && ourPlayer.playerInventory.contains("fuel") 
				|| operand.equals("lamp") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("match") && ourPlayer.playerInventory.contains("fuel") == false
				|| operand.equals("lamp") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("match") == false && ourPlayer.playerInventory.contains("fuel") == false)
		{
			System.out.println("You can't use your lamp without a match and fuel!");
		}
		//This will put the fuel in the lamp if the user's Inventory contains a lamp.
		else if(operand.equals("fuel can") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("lamp"))
		{
			fuel = true;
			System.out.println("You have put the fuel in the lamp.");
			ourPlayer.playerInventory.remove(operand);
		}
		//If the user's Inventory does not contain a lamp, it has no where to put the fuel in!
		else if(operand.equals("fuel can") && ourPlayer.playerInventory.contains(operand) && ourPlayer.playerInventory.contains("lamp") == false)
		{
			System.out.println("You don't have a lamp to put your fuel in!");
		}
		//These next statements check if there is a locked door anywhere, and if they have a key in their Inventory, 
		//it will unlock the room and move them into the room
		else if(operand.equals("key") && currentRoom.south.isLocked && ourPlayer.playerInventory.contains(operand))
		{
			currentRoom.south.isLocked = false;
			ourPlayer.playerInventory.remove(operand);
			System.out.println("You have unlocked the door to your South and moved into the Room.");
			currentRoom = currentRoom.south;
			System.out.println(currentRoom.toString());
			currentRoom.roomInventory.inventoryDescriptions();
			
		}
		else if(operand.equals("key") && currentRoom.north.isLocked && ourPlayer.playerInventory.contains(operand))
		{
			currentRoom.north.isLocked = false;
			currentRoom = currentRoom.north;
			System.out.println("You have unlocked the door to your North and moved into the Room.\n");			
			ourPlayer.playerInventory.remove(operand);
			System.out.println(currentRoom.toString());
			currentRoom.roomInventory.inventoryDescriptions();
		}	
		//This statement reads in "skeleton key" from the user and opens the final room, if they 
		//have a skeleton key in their Inventory.
		else if(operand.equals("skeleton key") && currentRoom.west.objectName.equals("Final") && ourPlayer.playerInventory.contains(operand))
		{
			currentRoom = currentRoom.west;
			ourPlayer.playerInventory.remove(operand);
			System.out.println(currentRoom.toString());
		}
		//This will tell them they can't go to the final room because they don't have a skeleton key.
		else if(operand.equals("skeleton key") && currentRoom.west.objectName.equals("Final") && ourPlayer.playerInventory.contains(operand) == false)
			System.out.println("You don't have a skeleton key!");
		else
			System.out.println("You either can't use that item, or do not have that item, or misspelled the item name.");
	}
}
