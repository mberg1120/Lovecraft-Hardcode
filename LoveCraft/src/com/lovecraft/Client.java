package com.lovecraft;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * 
 * @author Michael
 * @author Steven
 * @author Nathan
 * @author Kellie
 * @version 1.11
 * @comments This is clearly not the completed version, but I am getting close. Should be done tomorrow.
 * @ToDo Use command finished (shouldn't be difficult) - Movement(including doors/walls/turning on lamp) - And... I believe that's all. 
 *
 *
 */

public class Client 
{
	public static void main(String[] args)
	{
		// Creates the grid variables used to go from room to room.
		int x = 1;
		// Input variable.
		String userInput;
		String Key = "There is a Key on the ground... It may be useful in the future";
		String skeletonKey = "This is a Skeleton Key... It will be useful in the future.";
		String lamp = "This lamp, when used with fuel and a match will light up the Cave.";
		String match = "This match, when used with fuel and a lamp, will light up the Cave.";
		Item.setDescription("key", "As you look at the key, you can tell it is rather shiny.");
		Item.setDescription("skeleton key", "This key is more unique than the other one. It has a skeleton head on it, and it is clear it will be useful.");
		Item.setDescription("match", "Perhaps you should combine this with a lamp and some fuel?");
		Item.setDescription("lamp", "If you add fuel and a match, this will probably light up the Cave.");
		// Creates a boolean to be use with the DoWhile loop later
		boolean keepGoing = true;
		//Sets up an array of the Room object, and will allow us
		//to go from north south east west and set up specific items 
		//for specific rooms (also allowing drop and take from room). 
		ArrayList<Room> Rooms = new ArrayList<Room>();
		Room A1 = new Room("Start");
		Room B1 = new Room("B1");
		Room A2 = new Room("A2");
		Room B2 = new Room("B2");
		Rooms.add(A1);
		Rooms.add(A2);
		Rooms.add(B2);
		Rooms.add(B1);
		System.out.println(Rooms.get(0));
		A1.initialItem(Key);
		
		
		
		// Prints the introduction to the user.
		System.out.println("Welcome user, this is Project Love Craft... You have found yourself lying on the ground," +
				"\nand it  appears you have a bump on the back of your head. As you look around, you only " +
				"\nhave 3 areas that are visible, and it appears you are stuck in a dungeon or cave of some sort. " +
				"\nOnce you go forth into this dungeon, you will find objects, it is your choice how to handle them. " +
				"\nYou will have the ability to receive hints in each area, and your first hint is that you must type" +
				"\n'go' and then the word North, West, East or South to move. Currently, you may only go east or south.");
		Scanner scan = new Scanner(System.in);
		// DoWhile loop that will continue looping until the user reaches the end, and then it will
		// terminate and continue forward. Most of the loop is self explanatory, will not need to comment
		// much on it. 
		do{
		userInput = scan.nextLine();
		userInput.toLowerCase();
		if(userInput.equals("go") )
		{
			System.out.println("Go where?");
		}
		else if(userInput.equals("north") || userInput.equals("n") || userInput.equals("go north"))
		{
			x -= 11;
			if(x == 25 || x == 26 || x == 36 || x == 37)
			{
				Room.Chasm(scan);
				x += 11;
			}
			else
				System.out.println(Rooms.get(x));
			System.out.println("You went North");
		}
		else if(userInput.equals("s") || userInput.equals("south") || userInput.equals("go south"))
		{
			x += 11;
			if(x == 25 || x == 26 || x == 36 || x == 37)
			{
				Room.Chasm(scan);
				x -= 11;
			}
			else
				Rooms.get(x);
			System.out.println("You went South");
		}
		else if(userInput.equals("e") || userInput.equals("east") || userInput.equals("go east"))
		{
			x++;
			if(x == 25 || x == 26 || x == 36 || x == 37)
			{
				Room.Chasm(scan);
				x--;
			}
			else
				System.out.println(Rooms.get(x));
			System.out.println("You went East");
		}
		else if(userInput.equals("w") || userInput.equals("west") || userInput.equals("go west"))
		{
			x--;
			if(x == 25 || x == 26 || x == 36 || x == 37)
			{
				Room.Chasm(scan);
				x++;
			}
			else
				System.out.println(Rooms.get(x));
			System.out.println("You went West");
		}
		else if (userInput.equals("take key") || userInput.equals("pick up key") || userInput.equals("pickup key") || userInput.equals("take") || userInput.equals("pick up") || userInput.equals("pickup"))
		{
			A1.addItem(Key);
		}
		else if (userInput.equals("take skeleton key") || userInput.equals("pick up skeleton key") || userInput.equals("pickup skeleton key") || userInput.equals("take") || userInput.equals("pick up") || userInput.equals("pickup") )
		{
			Room.addItem(skeletonKey);
		}
		else if (userInput.equals("take lamp") || userInput.equals("pick up lamp") || userInput.equals("pickup lamp") || userInput.equals("take") || userInput.equals("pick up") || userInput.equals("pickup"))
		{
			Room.addItem(lamp);
		}
		else if (userInput.equals("take match") || userInput.equals("pick up match") || userInput.equals("pickup match") || userInput.equals("take") || userInput.equals("pick up") || userInput.equals("pickup"))
		{
			Room.addItem(match);
		}
		else if (userInput.equals("drop key") || userInput.equals("drop"))
		{
			A1.dropItem(Key);
		}
		else if(userInput.equals("drop skeleton key") || userInput.equals("drop"))
		{
			Room.dropItem(skeletonKey);
		}
		else if(userInput.equals("drop lamp") ||userInput.equals("drop"))
		{
			Room.dropItem(lamp);
		}
		else if(userInput.equals("drop match") || userInput.equals("drop"))
		{
			Room.dropItem(match);
		}
		else if (userInput.equals("use"))
		{
			
		}
		else if (userInput.equals("look key"))
		{
			Item.lookItem("key");
		}
		else if (userInput.equals("look skeleton key"))
		{
			Item.lookItem("skeleton key");
		}
		else if (userInput.equals("look lamp"))
		{
			Item.lookItem("lamp");
		}
		else if (userInput.equals("look match"))
		{
			Item.lookItem("match");
		}
		else if (userInput.equals("i") || userInput.equals("inventroy"))
		{
//			Item.inventoryContents();
		}
		else
			System.out.println("You seem to have used an incorrect command, or you have added an extra space.");
		if(x == 5)
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
}
