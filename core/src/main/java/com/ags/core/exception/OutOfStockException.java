package com.ags.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutOfStockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1162189645290382145L;
	
	private String message;

}
