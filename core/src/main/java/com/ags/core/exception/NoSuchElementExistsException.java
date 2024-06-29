package com.ags.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoSuchElementExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6976251421538967568L;

	private String message;

}
