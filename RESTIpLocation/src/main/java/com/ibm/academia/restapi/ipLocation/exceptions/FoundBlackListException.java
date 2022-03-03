package com.ibm.academia.restapi.ipLocation.exceptions;

public class FoundBlackListException extends RuntimeException 
{
	public FoundBlackListException(String message)
	{
		super(message);
	}
	
	private static final long serialVersionUID = 6407196044322865851L;
}