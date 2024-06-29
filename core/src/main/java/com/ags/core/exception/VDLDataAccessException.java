package com.ags.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VDLDataAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4926603743014337652L;

	private String message;

}
