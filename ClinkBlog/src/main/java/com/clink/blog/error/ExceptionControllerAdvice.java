package com.clink.blog.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clink.blog.vm.ResultVm;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(value = RoleBasedException.class)
	public ResponseEntity<ResultVm> handle(final RoleBasedException exception) {

		ResultVm resultVm = new ResultVm();
		resultVm.resultMessages.add(exception.getMessage());
		return new ResponseEntity<ResultVm>(resultVm, HttpStatus.UNAUTHORIZED);
	}
}