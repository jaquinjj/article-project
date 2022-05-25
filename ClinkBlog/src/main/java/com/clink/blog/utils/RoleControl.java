package com.clink.blog.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.clink.blog.error.RoleBasedException;
import com.clink.blog.vm.ResultVm;

@Aspect
@Component
public class RoleControl {

	@Autowired
	private UserDetailsService uds;

	@Before("execution(public * *(..)) && @annotation(RepeatedRoleUser)")
	public Object doIdempotentOperation(JoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		java.lang.reflect.Method method = signature.getMethod();
		RepeatedRoleUser roleUser = method.getAnnotation(RepeatedRoleUser.class);

		RoleUser[] roleUsers = roleUser.value();

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String userName = request.getRemoteUser();
		UserDetails userDetails = uds.loadUserByUsername(userName);

		List<String> listUserRoleList = userDetails.getAuthorities().stream().map(x -> x.getAuthority())
				.collect(Collectors.toList());

		List<String> authenticateUserRoleList = Arrays.asList(roleUsers).stream().map(x -> x.role_name())
				.collect(Collectors.toList());

 		
 		@SuppressWarnings("unchecked")
		Collection<String> intersection = CollectionUtils.intersection(listUserRoleList, authenticateUserRoleList);


		if (intersection.size() == 0) {
			throw new RoleBasedException("User:" + userName + ", Role:" + String.join(", ", listUserRoleList)
					+ " haven't authorize this page!");
		}

		return new ResultVm();

	}

}
