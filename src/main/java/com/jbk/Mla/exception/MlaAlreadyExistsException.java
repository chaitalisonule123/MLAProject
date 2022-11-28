package com.jbk.Mla.exception;

public class MlaAlreadyExistsException extends RuntimeException{
	//default constructor
	public MlaAlreadyExistsException(String msg){
		
		super(msg);
		//raha se msg runtimeexception(parent class or super class) ko de diya
		
	}
	
	

}
