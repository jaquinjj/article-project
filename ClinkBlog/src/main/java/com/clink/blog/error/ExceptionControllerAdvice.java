package com.clink.blog.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clink.blog.error.exceptions.RoleBasedException;
import com.clink.blog.vm.ResultVm;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(value = RoleBasedException.class)
	public ResponseEntity<ResultVm> handle(final RoleBasedException exception) {

		ResultVm resultVm = new ResultVm();
		resultVm.resultMessages.add(exception.getMessage());
		return new ResponseEntity<ResultVm>(resultVm, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ResultVm> processUnmergeException(final MethodArgumentNotValidException ex) {

		ResultVm resultVm = new ResultVm();
		resultVm.isSuccess = false;

		List<String> list = ex.getBindingResult().getAllErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());

		resultVm.resultMessages.addAll(list);

		return new ResponseEntity<>(resultVm, HttpStatus.BAD_REQUEST);
	}
}