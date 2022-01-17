package com.sclw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DataInsertionDuplicateException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataInsertionDuplicateException(String msg) {
		super(msg);
	}
	
	public DataInsertionDuplicateException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
