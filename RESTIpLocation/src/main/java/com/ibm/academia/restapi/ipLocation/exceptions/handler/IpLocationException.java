package com.ibm.academia.restapi.ipLocation.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.restapi.ipLocation.exceptions.FoundBlackListException;

@ControllerAdvice
public class IpLocationException {
	@ExceptionHandler(value = FoundBlackListException.class)
	public ResponseEntity<Object> notExistException(FoundBlackListException exception)
	{
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("Error", exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
