/**
 * 
 */
package com.book.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String details;

	
}
