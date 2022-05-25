package com.clink.blog.error;

public class RoleBasedException extends Exception {
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
