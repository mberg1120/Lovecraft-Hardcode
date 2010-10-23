package com.lovecraft;

public abstract class GameObject {
	static Inventory inventory = new Inventory();
		
	protected String description = "";
	protected String objectName = "";


	public void setDescription(String pDescription)
	{
		description = pDescription;
	}

	public String getDescription()
	{
		return description;
	}
	
	
	
//	 can the object be used with the given parameters?
//	 i.e. can torch be used with fire to create light?
	 
	public abstract boolean useObject();
	

}
