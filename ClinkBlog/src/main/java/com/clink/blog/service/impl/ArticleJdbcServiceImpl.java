package com.clink.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clink.blog.dao.ArticleJdbcRepository;
import com.clink.blog.dao.ArticleRepository;
import com.clink.blog.dto.ArticlePagedDto;
import com.clink.blog.dto.PagedDataDto;
import com.clink.blog.service.ArticleJdbcService;

@Service
public class ArticleJdbcServiceImpl implements ArticleJdbcService{

	@Autowired
	ArticleJdbcRepository articleJdbcRepository;
	
	@Override
	public ArticlePagedDto getPagedArticleList(PagedDataDto pagedData) {
 		return articleJdbcRepository.getPagedArticleList(pagedData);
	}

}
