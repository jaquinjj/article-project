package com.clink.blog.dao;

import com.clink.blog.dto.ArticlePagedDto;
import com.clink.blog.dto.PagedDataDto;

public interface ArticleJdbcRepository {
	ArticlePagedDto getPagedArticleList(PagedDataDto pagedData);
}
