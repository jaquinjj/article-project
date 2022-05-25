package com.clink.blog.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatedRoleUser
{
	RoleUser[] value();
}
