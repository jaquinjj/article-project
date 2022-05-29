package com.clink.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clink.blog.dto.PagedDataDto;
import com.clink.blog.dto.ResultDto;
import com.clink.blog.service.ArticleJdbcService;

@RestController
@RequestMapping("/api/articlejdbc")
public class ArticleJdbcController {

	@Autowired
	ArticleJdbcService articleJdbcService;
	
	@RequestMapping(value = "/getPagedArticleList",method = RequestMethod.GET)
	public ResponseEntity<ResultDto> getPagedArticleList(
			@RequestParam(name="page",defaultValue = "0",required = false ) Integer page ,
			@RequestParam(name="pageSize",defaultValue = "5",required = false ) Integer pageSize
	)
	{
		ResultDto resultDto = new ResultDto();
		resultDto.isSuccess = true;
		resultDto.resultSet = articleJdbcService.getPagedArticleList(new PagedDataDto(page,pageSize));
		resultDto.resultMessages.add("İşlem Başarılı");
		
		return new ResponseEntity<ResultDto>(resultDto,HttpStatus.OK);
	}
}
