package com.clink.blog.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.core.userdetails.UserDetailsService;

@Aspect
@Component
public class RoleControl {

	@Autowired
	private UserDetailsService uds;

	@Around("execution(public * *(..)) && @annotation(RoleUser)")
	public Object doIdempotentOperation(ProceedingJoinPoint joinPoint) throws Throwable {
		Object retVal = joinPoint.proceed();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		java.lang.reflect.Method method = signature.getMethod();

		RoleUser myAnnotation = method.getAnnotation(RoleUser.class);

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String userName = request.getRemoteUser();

		UserDetails userDetails = uds.loadUserByUsername(userName);

		List<String> listUserRoleList = userDetails.getAuthorities().stream().map(x -> x.getAuthority())
				.collect(Collectors.toList());

		if (!listUserRoleList.contains(myAnnotation.role_name())) {
			throw new Exception("Kullanıcınız bu role tanımlı değildir.");
		}

		return retVal;
	}

}
