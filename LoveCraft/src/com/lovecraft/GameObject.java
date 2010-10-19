package com.lovecraft;

public abstract class GameObject {
	
	protected String description;
	protected String objectName;
	
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return objectName;
	}
	
	public abstract boolean useObject();

}
