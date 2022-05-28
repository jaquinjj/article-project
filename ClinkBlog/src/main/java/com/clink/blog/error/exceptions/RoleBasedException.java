package com.clink.blog.error.exceptions;

public class RoleBasedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleBasedException () {

    }

    public RoleBasedException(String message) {
        super (message);
    }

    public RoleBasedException(Throwable cause) {
        super (cause);
    }

    public RoleBasedException(String message, Throwable cause) {
        super (message, cause);
    }

}
