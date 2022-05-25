package com.clink.blog.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.ElementType.METHOD;

  

@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface RoleUser {
	String role_name() ;

}