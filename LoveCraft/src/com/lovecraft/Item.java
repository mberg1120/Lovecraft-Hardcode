package com.lovecraft;

import java.util.Vector;

public class Item extends GameObject {
	protected boolean itemInRoom = true;

	@Override
	public boolean useObject() {

		// logic needed to answer:
		// can the combination of objects be used together?

		return false;
	}
	

}
