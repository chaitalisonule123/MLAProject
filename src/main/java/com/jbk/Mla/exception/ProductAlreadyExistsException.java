package com.jbk.Mla.exception;

public class ProductAlreadyExistsException extends RuntimeException{
	//default constructor
	public ProductAlreadyExistsException(String msg){
		
		super(msg);
		//raha se msg runtimeexception(parent class or super class) ko de diya
		
	}
	
	

}
