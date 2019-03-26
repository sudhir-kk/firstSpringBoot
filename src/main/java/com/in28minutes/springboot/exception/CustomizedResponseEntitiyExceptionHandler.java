package com.in28minutes.springboot.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.in28minutes.springboot.user.UserNotFoundException;


@RestControllerAdvice
@RestController
public class CustomizedResponseEntitiyExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exp, WebRequest req) throws Exception {
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), exp.getMessage(), req.getDescription(false));
		return	new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exp, WebRequest req) throws Exception {
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), exp.getMessage(), req.getDescription(false));
		return	new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exp, HttpHeaders headers, HttpStatus status, WebRequest req) {
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), "Validation Failed", exp.getBindingResult().toString());
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
}
