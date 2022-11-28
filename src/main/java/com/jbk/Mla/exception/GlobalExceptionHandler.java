package com.jbk.Mla.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//sari exception is class me handle hogi
//ProductAlreadyExistsException me exception hai so usaka object banaya.
//getMassage method parent class se aayi(run time exception)
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public static HashMap<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("Time", new Date());
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return map;
	}
	
	@ExceptionHandler(MlaAlreadyExistsException.class)
	public ResponseEntity<String>ProductAlreadyExistsException(MlaAlreadyExistsException ex){
		String msg =ex.getMessage();
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
		
	}
	
		
		
	
	
	
	
	

}
