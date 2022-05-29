package com.clink.blog.service;

import com.clink.blog.dto.ArticlePagedDto;
import com.clink.blog.dto.PagedDataDto;

public interface ArticleJdbcService {
	ArticlePagedDto getPagedArticleList(PagedDataDto pagedData);
}
