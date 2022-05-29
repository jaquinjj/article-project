package com.clink.blog.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clink.blog.dto.ResultDto;
import com.clink.blog.error.exceptions.RoleBasedException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(value = RoleBasedException.class)
	public ResponseEntity<ResultDto> handle(final RoleBasedException exception) {

		ResultDto ResultDto = new ResultDto();
		ResultDto.resultMessages.add(exception.getMessage());
		return new ResponseEntity<ResultDto>(ResultDto, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ResultDto> processUnmergeException(final MethodArgumentNotValidException ex) {

		ResultDto ResultDto = new ResultDto();
		ResultDto.isSuccess = false;

		List<String> list = ex.getBindingResult().getAllErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());

		ResultDto.resultMessages.addAll(list);

		return new ResponseEntity<>(ResultDto, HttpStatus.BAD_REQUEST);
	}
}