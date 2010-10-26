package com.lovecraft;
/**
 * Creates a player, and creates an inventory with it. There can be more than one player.
 * @author Michael
 *
 */
public class Player {
	Inventory playerInventory;
	/**
	 * Physically makes the player, and creates a playerInventory for that player.
	 */
	public Player()
	{
		playerInventory = new Inventory();
	}
}
