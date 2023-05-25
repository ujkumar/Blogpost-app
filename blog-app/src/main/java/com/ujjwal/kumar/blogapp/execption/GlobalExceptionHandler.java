package com.ujjwal.kumar.blogapp.execption;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(ResourceNotFoundExecption.class)
//	public ResponseEntity<ApiResponse> handelResourceNotFoundExecption(ResourceNotFoundExecption ex){
//		String message = ex.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message,false);
//		return new ResponseEntity<ApiResonse>(apiResponse,HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{ //Here getBindingResult will give all the output and getAllErrors fetch all those errors.
			String filedName = ((FieldError)error).getField(); // from error object we are not getting getField so we type cast error to FieldError and then we fetch all the error.
			String message = error.getDefaultMessage();//from error we can get all the error message so we fetch and put into a string variable.
			resp.put(filedName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
}
