package com.book.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BookExceptionHandler {

	@ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(BookNotFoundException ine){
        BookErrorResponse errorResponse = new BookErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ine.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<BookErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
	        ex.getBindingResult().toString());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	  } 

    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(Exception ex){
    	BookErrorResponse errorResponse = new BookErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<BookErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
